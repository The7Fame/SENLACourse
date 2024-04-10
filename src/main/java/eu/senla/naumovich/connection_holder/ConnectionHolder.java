package eu.senla.naumovich.connection_holder;

import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Component
public class ConnectionHolder {
    private final DataSource dataSource;
    private final Map<String, Connection> thread2connection = new HashMap<>();
    private final Queue<Connection> connections = new LinkedList<>();

    public ConnectionHolder(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Connection getConn(){
        String currThread = Thread.currentThread().getName();
        if(thread2connection.containsKey(currThread)){
            return thread2connection.get(currThread);
        }
        synchronized (this) {
            Connection conn = connections.poll();
                if (!thread2connection.containsValue(conn)){
                    if (conn != null && isOpen(conn)) {
                        return conn;
                    }
                }
            return createConn();
        }
    }

    public boolean isOpen(Connection conn){
        try {
            return !conn.isClosed();
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

    public synchronized Connection getTransactionConn(){
        String currThread = Thread.currentThread().getName();
        if(thread2connection.containsKey(currThread)){
            Connection conn = thread2connection.get(currThread);
            if(isOpen(conn)){
                return conn;
            }else {
                System.out.println("Can not connect");
            }
        }synchronized (this){
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
        Connection conn = thread2connection.remove(currThread);
        connections.add(conn);
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
