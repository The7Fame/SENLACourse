package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.dao.repository.common.AbstractRepository;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface CartRepository extends AbstractRepository<Cart, Long> {
    public List<Book> getBooksFromCart(long userId);
    public double calculateTotalPrice(long userId);
}
