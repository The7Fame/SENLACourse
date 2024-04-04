package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.OrderRepository;
import eu.senla.naumovich.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    final List<Order> orders = new ArrayList<>();
    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public Order getById(Order order) {
        return orders.stream()
                .filter(o -> o.getId() == order.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Order update(Order order) {
        for(Order o : orders){
            if(order.getId() == o.getId()){
                o.setTotalPrice(order.getTotalPrice());
                return o;
            }
        }
        return null;
    }

    @Override
    public Order create(Order order) {
        orders.add(order);
        return order;
    }

    @Override
    public void delete(Order order) {
        orders.removeIf(o -> o.getId() == order.getId());
    }
}
