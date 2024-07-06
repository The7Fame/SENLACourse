package eu.senla.naumovich.repositories;

import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select c.book from Cart c where c.user.id = :id")
    Page<Book> getBooksFromCart(@Param("id") long userId, Pageable pageable);

    @Query("select c.book from Cart c where c.user.id = :id")
    List<Book> getBooksFromCart(@Param("id") long userId);
}
