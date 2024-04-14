package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.User;
import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepositoryImpl extends AbstractDao<Long, User> implements UserRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getUserByName(String userName) {
        String jbqlQuery = "select u from User u where u.name = :name";
        TypedQuery<User> query =entityManager.createQuery(jbqlQuery, User.class);
        query.setParameter("name", userName);
        return query.getSingleResult();
    };

    @Override
    public User getUserByEmail(String userEmail) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        query.where(criteriaBuilder.equal(userRoot.get("email"), userEmail));
        return entityManager.createQuery(query).getSingleResult();
    };

    @Override
    public User getUserByIdGraph(Integer id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("graph.User.associations");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", entityGraph);
        User user = entityManager.find(User.class, id, hints);
        return user;
    };
}
