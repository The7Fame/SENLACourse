package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.book.BookToCartDto;
import eu.senla.naumovich.dto.cart.CartDto;
import eu.senla.naumovich.dto.cart.CartShortDto;
import eu.senla.naumovich.security.SecurityUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CartService {
    @Transactional
    public CartDto addBookToCart(SecurityUser securityUser, BookToCartDto book);

    public List<BookShortDto> booksInCart(SecurityUser securityUser, int page, int size);

    public double calculateTotalPrice(long userId);

    public List<CartShortDto> getAll(int page, int size, String sort);

    public List<BookShortDto> getById(Long id, int page, int size);

    @Transactional
    public CartDto update(CartDto cart);

    @Transactional
    public CartDto create(CartDto cart);

    @Transactional
    void delete(Long id);
}
