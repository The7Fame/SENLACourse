package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Cart;

import java.util.List;

public interface CartRepository {
    List<Cart> getAll();

    Cart getById(Cart cart);

    Cart update(Cart cart);

    Cart create(Cart cart);

    void delete(Cart cart);
}
