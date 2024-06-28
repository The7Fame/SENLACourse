package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.review.ReviewForBookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.mapper.ReviewMapper;
import eu.senla.naumovich.repositories.BookRepository;
import eu.senla.naumovich.services.impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @Mock
    private BookMapper mapper;
    @Mock
    private ReviewMapper reviewMapper;
    @Mock
    BookRepository repository;
    @InjectMocks
    private BookServiceImpl service;

    @Test
    public void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Book book = new Book();
        Page<Book> bookPage = new PageImpl<>(Collections.singletonList(book));
        when(repository.findAll(pageable)).thenReturn(bookPage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(BookShortDto.builder().build()));
        List<BookShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    public void getByIdTest() {
        Book book = Generator.createBook();
        BookDto bookDto = Generator.createBookDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(book));
        when(mapper.toDto(book)).thenReturn(bookDto);
        BookDto result = service.getById(1L);
        Assertions.assertEquals(bookDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(book);
    }

    @Test
    public void updateTest() {
        Book book = Generator.createBook();
        BookDto bookDto = Generator.createBookDto();
        when(mapper.toEntity(bookDto)).thenReturn(book);
        when(repository.save(book)).thenReturn(book);
        when(mapper.toDto(book)).thenReturn(bookDto);
        BookDto result = service.update(bookDto);
        Assertions.assertEquals(bookDto, result);
        verify(repository).save(book);
        verify(mapper).toEntity(bookDto);
        verify(mapper).toDto(book);
    }

    @Test
    public void createTest() {
        Book book = Generator.createBook();
        BookDto bookDto = Generator.createBookDto();
        when(mapper.toEntity(bookDto)).thenReturn(book);
        when(repository.save(book)).thenReturn(book);
        when(mapper.toDto(book)).thenReturn(bookDto);
        BookDto result = service.create(bookDto);
        Assertions.assertEquals(bookDto, result);
        verify(repository).save(book);
        verify(mapper).toEntity(bookDto);
        verify(mapper).toDto(book);
    }

    @Test
    public void deleteTest() {
        Book book = Generator.createBook();
        doNothing().when(repository).deleteById(book.getId());
        service.delete(book.getId());
        verify(repository).deleteById(book.getId());
    }

    @Test
    public void getReviewsByBookIdTest(){
        Long bookId = 1L;
        Page<Review> reviewPage = new PageImpl<>(Collections.emptyList());
        when(repository.getReviewsByBookId(bookId, PageRequest.of(0, 5))).thenReturn(reviewPage);
        when(reviewMapper.toReviewForBookDtoList(reviewPage.getContent())).thenReturn(Collections.emptyList());
        List<ReviewForBookDto> result = service.getReviewsByBookId(bookId, 1, 5);
        assertNotNull(result);
        verify(repository).getReviewsByBookId(bookId, PageRequest.of(0, 5));
        verify(reviewMapper).toReviewForBookDtoList(reviewPage.getContent());
    }

    @Test
    public void getPopularBooks(){
        Pageable pageable = PageRequest.of(0, 5);
        Page<Book> bookPage = new PageImpl<>(Collections.emptyList());
        when(repository.getPopularBooks(PageRequest.of(0, 5))).thenReturn(bookPage);
        when(mapper.toDtoList(bookPage.getContent())).thenReturn(Collections.emptyList());
        List<BookShortDto> result = service.getPopularBooks(1, 5);
        assertNotNull(result);
        verify(repository).getPopularBooks(pageable);
        verify(mapper).toDtoList(bookPage.getContent());
    }
}
