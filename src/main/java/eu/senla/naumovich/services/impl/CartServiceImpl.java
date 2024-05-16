package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.CartRepository;
import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.cart.CartDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Cart;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.mapper.CartMapper;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.CartService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
        private final CartRepository cartRepository;
        private final CartMapper cartMapper;
        private final UserService userService;
        private final BookMapper bookMapper;

        @Override
        public List<CartDto> getAll(int size, int page) {
                List<Cart> carts = cartRepository.getAll(size, page);
                return cartMapper.toDtoList(carts);
        }

        @Override
        public CartDto getById(Long id) {
                return cartMapper.toDto(cartRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));
        }

        @Override
        public CartDto update(CartDto cart) {
                return cartMapper.toDto(cartRepository.update(cartMapper.toEntity(cart)));
        }

        @Override
        public CartDto create(CartDto cart) {
                return cartMapper.toDto(cartRepository.create(cartMapper.toEntity(cart)));
        }

        @Override
        public void delete(Long id) {
                cartRepository.deleteById(id);
        }

        @Override
        public double booksInCart(long userId) {
                return cartRepository.calculateTotalPrice(userId);
        }

        @Transactional
        public CartDto addBookToCart(SecurityUser securityUser, BookDto book) {
                UserDto user = userService.getById(securityUser.getId());
                CartDto cart = CartDto.builder()
                        .user(user)
                        .book(book).build();
                return create(cart);
        }

        @Override
        public List<BookDto> booksInCart(SecurityUser securityUser) {
                UserDto user = userService.getById(securityUser.getId());
                List<Book> books = cartRepository.getBooksFromCart(user.getId());
                return bookMapper.toDtoList(books);
        }
}
