package eu.senla.naumovich.connection_holder;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ConnectionHolder {
    private final DataSource dataSource;
    private final Map<String, Connection> thread2connection = new HashMap<>();
    private final List<Connection> connections = new ArrayList<>();

    public ConnectionHolder(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Connection getConn(){
        String currThread = Thread.currentThread().getName();
        if(thread2connection.containsKey(currThread)){
            return thread2connection.get(currThread);
        }else {
            for(Connection conn : connections){
                if (!thread2connection.containsValue(conn)){
                    if (isOpen(conn)){
                        return conn;
                    }
                }
            }
            return createConn();
        }
    }

    public boolean isOpen(Connection conn){
        try {
            if(conn.isClosed()){
                return false;
            }else {
                return true;
            }
        }catch (SQLException e){}
        return false;
    }

    public Connection createConn(){
        try {
            Connection conn = dataSource.getConnection();
            connections.add(conn);
            return conn;
        }catch (SQLException e){}
        return null;
    }

    public Connection getTransactionConn(){
        String currThread = Thread.currentThread().getName();
        if(thread2connection.containsKey(currThread)){
            Connection conn = thread2connection.get(currThread);
            if(isOpen(conn)){
                return conn;
            }else {
                System.out.println("Can not connect");
            }
        }else {
            try {
                Connection conn = createConn();
                conn.setAutoCommit(false);
                thread2connection.put(currThread, conn);
                return conn;
            }catch (SQLException e){}
        }
        return null;
    }

    public void releaseConn(){
        String currThread = Thread.currentThread().getName();
        thread2connection.remove(currThread);
    }

    public void commit(Connection conn){
        try {
            conn.commit();
        }catch (SQLException e){}
    }

    public void rollback(Connection connection){
        try {
            connection.rollback();
        }catch (SQLException e){}
    }

    @PreDestroy
    private void destroy(){
        for(Connection conn : connections){
            try {
                if(!conn.isClosed()){
                    conn.close();
                }
            }catch (SQLException e){}
        }
    }
}
