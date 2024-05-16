package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.cart.CartDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.common.AbstractService;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CartService extends AbstractService<CartDto> {
    public double booksInCart(long userId);
    public CartDto addBookToCart(SecurityUser securityUser, BookDto book);
    public List<BookDto> booksInCart(SecurityUser securityUser);
}
