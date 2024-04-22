package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.dao.repository.common.AbstractRepository;
import eu.senla.naumovich.entities.User;

public interface UserRepository extends AbstractRepository<User, Long> {
    public User getUserByName(String userName);

    public User getUserByEmail(String userEmail);

    public User getUserByIdGraph(Integer id);
}
