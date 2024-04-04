package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    final List<User> users = new ArrayList<>();
    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(User user) {
        return users.stream()
                .filter(u -> u.getId() == user.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public User update(User user) {
        for(User u : users){
            if(user.getId() == u.getId()){
                u.setName(user.getName());
                return u;
            }
        }
        return null;
    }

    @Override
    public User create(User user) {
        users.add(user);
        return user;
    }

    @Override
    public void delete(User user) {
        users.removeIf(u -> u.getId() == user.getId());
    }
}
