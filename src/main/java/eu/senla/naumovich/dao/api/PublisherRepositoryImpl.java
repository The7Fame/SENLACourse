package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.connection_holder.ConnectionHolder;
import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.entities.Address;
import eu.senla.naumovich.entities.Publisher;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
    private final ConnectionHolder connectionHolder;
    private final static String CREATE_PUBLISHER = """
            INSERT INTO publishers(publisher_name) VALUES(?)
            """;
    private final static String CREATE_ADDRESS = """
            INSERT INTO addresses(city, street, index, publisher_id) VALUES(?,?,?,?)
            """;
    private final static String SELECT_ALL_PUBLISHERS_WITH_ADDRESSES = """
            SELECT p.id, p.publisher_name, a.id as address_id, a.city, a.street, a.index FROM publishers p
            JOIN addresses a on p.id=a.publisher_id;
            """;

    private final static String SELECT_BY_ID = """
            SELECT p.id, p.publisher_name, a.id as address_id, a.city, a.street, a.index FROM publishers p
            JOIN addresses a on p.id=a.publisher_id
            WHERE p.id = ?;
            """;
    private final static String DELETE_PUBLISHER = """
            DELETE FROM publishers WHERE id = ?
            """;
    private final static String UPDATE_PUBLISHER_NAME = """
            UPDATE publishers SET publisher_name = ? WHERE id = ?
            """;

    public PublisherRepositoryImpl(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }

    @Override
    public List<Publisher> getAll() {
        List<Publisher> publisherList = new ArrayList<>();
        Connection conn = connectionHolder.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_PUBLISHERS_WITH_ADDRESSES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Publisher publisher = new Publisher();
                Address address = new Address();
                publisher.setId(resultSet.getLong("id"));
                publisher.setPublisherName(resultSet.getString("publisher_name"));
                address.setId(resultSet.getLong("address_id"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setIndex(resultSet.getLong("index"));
                publisher.setAddress(address);
                publisherList.add(publisher);
            }
        }catch (SQLException e){}
        return publisherList;
    }

    @Override
    public Publisher getById(Publisher publisher) {
        Publisher p = new Publisher();
        Connection conn = connectionHolder.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_BY_ID);
            preparedStatement.setLong(1, publisher.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Address address = new Address();
                p.setId(resultSet.getLong("id"));
                p.setPublisherName(resultSet.getString("publisher_name"));
                address.setId(resultSet.getLong("address_id"));
                address.setCity(resultSet.getString("city"));
                address.setStreet(resultSet.getString("street"));
                address.setIndex(resultSet.getLong("index"));
                p.setAddress(address);
            }

        }catch (SQLException e){}
        return p;
    }

    @Override
    public Publisher update(Publisher publisher) {
        Connection conn = connectionHolder.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_PUBLISHER_NAME);
            preparedStatement.setString(1, publisher.getPublisherName());
            preparedStatement.setLong(2, publisher.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){}
        return getById(publisher);
    }

    @Override
    public Publisher create(Publisher publisher) {
        Connection conn = connectionHolder.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(CREATE_PUBLISHER);
            preparedStatement.setString(1, publisher.getPublisherName());
            preparedStatement.execute();

        }catch (SQLException e){}
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(CREATE_ADDRESS);
            preparedStatement.setString(1, publisher.getAddress().getCity());
            preparedStatement.setString(2, publisher.getAddress().getStreet());
            preparedStatement.setLong(3, publisher.getAddress().getIndex());
            preparedStatement.setLong(4, publisher.getId());
            preparedStatement.execute();
        }catch (SQLException e){}
        return publisher;
    }

    @Override
    public void delete(Publisher publisher) {
        Connection conn = connectionHolder.getConn();
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_PUBLISHER);
            preparedStatement.setLong(1, publisher.getId());
            preparedStatement.execute();
        }catch (SQLException e){}
    }
}
