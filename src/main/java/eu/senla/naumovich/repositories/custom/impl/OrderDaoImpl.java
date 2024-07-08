package eu.senla.naumovich.repositories.custom.impl;

import eu.senla.naumovich.entities.Order;
import eu.senla.naumovich.repositories.custom.OrderDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    @Autowired
    EntityManager entityManager;

    @Override
    public Optional<Order> getByUserAndOrderById(long userId, long orderId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        Predicate userPredicate = criteriaBuilder.equal(orderRoot.get("user").get("id"), userId);
        Predicate orderPredicate = criteriaBuilder.equal(orderRoot.get("id"), orderId);
        criteriaQuery.where(criteriaBuilder.and(userPredicate, orderPredicate));
        criteriaQuery.orderBy(criteriaBuilder.asc(orderRoot.get("totalPrice")));
        try {
            return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
        }catch (NoResultException ex){
            return Optional.empty();
        }
    }
}
