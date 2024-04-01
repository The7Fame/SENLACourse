package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository{
    final List<Order> orders = new ArrayList<>();
    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public Order getById(Order order) {
        for(Order o : orders){
            if(o.getId() == order.getId()){
                return o;
            }
        }
        return null;
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
        for (int i = 0; i < orders.size(); i++) {
            if (order.getId() == orders.get(i).getId()) {
                orders.remove(i);
            }
        }
    }
}
