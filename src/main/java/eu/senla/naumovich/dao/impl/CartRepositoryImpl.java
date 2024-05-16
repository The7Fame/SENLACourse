package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.CartRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Cart;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.entities.Review;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class CartRepositoryImpl extends AbstractDao<Long, Cart> implements CartRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    protected Class<Cart> getEntityClass() {
        return Cart.class;
    }

    public List<Book> getBooksFromCart(long userId){
        String jbqlQuery = "select c.book from Cart c where c.user.id = :id";
        TypedQuery<Book> query = entityManager.createQuery(jbqlQuery, Book.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Override
    public double calculateTotalPrice(long userId) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<Book> books = getBooksFromCart(userId);
        System.out.println(books);
        for(Book book : books){
            BigDecimal bookPrice = book.getPrice();
            Optional<List<Promotion>> promotions = Optional.ofNullable(book.getPromotions());
            if(promotions.isPresent()) {
                BigDecimal maxPercent = promotions.get().stream().map(Promotion::getPercent).max(BigDecimal::compareTo).orElse(BigDecimal.ZERO);
                BigDecimal promotionAmount = bookPrice.multiply(maxPercent).divide(BigDecimal.valueOf(100));
                bookPrice = bookPrice.subtract(promotionAmount);
            }
            totalPrice = totalPrice.add(bookPrice);
        }
        return totalPrice.doubleValue();
    }
}
