package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.book.BookToCartDto;
import eu.senla.naumovich.dto.cart.CartDto;
import eu.senla.naumovich.dto.cart.CartShortDto;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Cart;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.exceptions.RecordExistsException;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.mapper.CartMapper;
import eu.senla.naumovich.repositories.CartRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.BookService;
import eu.senla.naumovich.services.service.CartService;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
        private final CartRepository cartRepository;
        private final CartMapper cartMapper;
        private final UserService userService;
        private final BookService bookService;
        private final BookMapper bookMapper;

        @Override
        public List<CartShortDto> getAll(int page, int size, String sort) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
                Page<Cart> cartPage = cartRepository.findAll(pageable);
                return cartMapper.toDtoList(cartPage.getContent());
        }

        @Override
        public List<BookShortDto> getById(Long id, int page, int size) {
                Pageable pageable = PageRequest.of(page - 1, size);
                Page<Book> bookPage = cartRepository.getBooksFromCart(id, pageable);
                return bookMapper.toDtoList(bookPage.getContent());
        }

        @Override
        public CartDto update(CartDto cart) {
                return cartMapper.toDto(cartRepository.save(cartMapper.toEntity(cart)));
        }

        @Override
        public CartDto create(CartDto cart) {
                try {
                        return cartMapper.toDto(cartRepository.save(cartMapper.toEntity(cart)));
                }catch (Exception e) {
                        throw new RecordExistsException("Record is exists");
                }
        }

        @Override
        public void delete(Long id) {
                cartRepository.deleteById(id);
        }

        @Transactional
        public CartDto addBookToCart(SecurityUser securityUser, BookToCartDto bookDto) {
                UserDto user = userService.getById(securityUser.getId());
                BookDto book = bookService.getById(bookDto.getId());
                CartDto cart = CartDto.builder()
                        .user(user)
                        .book(book).build();
                return create(cart);
        }

        @Override
        public List<BookShortDto> booksInCart(SecurityUser securityUser, int page, int size) {
                Pageable pageable = PageRequest.of(page - 1, size);
                UserDto user = userService.getById(securityUser.getId());
                Page<Book> books = cartRepository.getBooksFromCart(user.getId(), pageable);
                return bookMapper.toDtoList(books.getContent());
        }

        @Override
        public double calculateTotalPrice(long userId) {
                BigDecimal totalPrice = BigDecimal.ZERO;
                List<Book> books = cartRepository.getBooksFromCart(userId);
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
