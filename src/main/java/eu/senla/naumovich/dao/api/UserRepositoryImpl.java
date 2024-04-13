package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends AbstractDao<Long, User> implements UserRepository {
    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
