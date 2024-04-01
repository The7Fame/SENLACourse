package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();

    User getById(User user);

    User update(User user);

    User create(User user);

    void delete(User user);
}
