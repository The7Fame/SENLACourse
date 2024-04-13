package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl extends AbstractDao<Long, Order> implements OrderRepository {
    @Override
    protected Class<Order> getEntityClass() {
        return Order.class;
    }
}
