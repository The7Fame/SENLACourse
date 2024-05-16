package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl extends AbstractDao<Long, Order> implements OrderRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    protected Class<Order> getEntityClass() {
        return Order.class;
    }

    @Override
    public List<Order> getOrdersByUserId(long userId, int page, int size) {
        String jbqlQuery = "select o from Order o where o.user.id = :userId";
        TypedQuery<Order> query = entityManager.createQuery(jbqlQuery, Order.class);
        query.setParameter("userId", userId);
        applyPagination(query, page, size);
        return query.getResultList();
    }

    @Override
    public Optional<Order> getByUserAndOrderById(long userId, int orderId) {
        String jbqlQuery = "select o from Order o where o.user.id = :userId and o.id = :orderId";
        TypedQuery<Order> query = entityManager.createQuery(jbqlQuery, Order.class);
        query.setParameter("userId", userId);
        query.setParameter("orderId", orderId);
        List<Order> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.getFirst());
    }
}
