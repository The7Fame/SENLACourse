package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.dto.CartDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.math.BigDecimal;
import java.util.List;

public interface CartService extends AbstractService<CartDto> {
    public double booksInCart(long userId);
}
