package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepositoryImpl implements CartRepository{
    final List<Cart> carts = new ArrayList<>();
    @Override
    public List<Cart> getAll() {
        return carts;
    }

    @Override
    public Cart getById(Cart cart) {
        for(Cart c : carts){
            if(c.getId() == cart.getId()){
                return c;
            }
        }
        return null;
    }

    @Override
    public Cart update(Cart cart) {
        for(Cart c : carts){
            if(cart.getId() == c.getId()){
                c.setBook(cart.getBook());
                return c;
            }
        }
        return null;
    }

    @Override
    public Cart create(Cart cart) {
        carts.add(cart);
        return cart;
    }

    @Override
    public void delete(Cart cart) {
        for (int i = 0; i < carts.size(); i++) {
            if (cart.getId() == carts.get(i).getId()) {
                carts.remove(i);
            }
        }
    }
}
