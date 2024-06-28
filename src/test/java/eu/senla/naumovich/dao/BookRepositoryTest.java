package eu.senla.naumovich.dao;

import eu.senla.naumovich.dao.common.BaseRepositoryTest;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.entities.enums.Genre;
import eu.senla.naumovich.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.List;


public class BookRepositoryTest extends BaseRepositoryTest {
    @Autowired
    BookRepository repository;

    @Test
    public void getBookByNameTest(){
        Page<Book> bookPage = repository.getBookByName("Eve", applyPage());
        Assertions.assertEquals(bookPage.getTotalElements(), 1);
    };

    @Test
    public void getPopularBooksTest(){
        Page<Book> bookPage = repository.getPopularBooks(applyPage());
        Assertions.assertEquals(bookPage.getTotalElements(), 1);
    };

    @Test
    public void getBooksByGenreAndTitleTest(){
        Page<Book> bookPage = repository.getBooksByGenreAndTitle(Genre.lookup(1), "Eve", applyPage());
        Assertions.assertEquals(bookPage.getTotalElements(), 1);
    };

    @Test
    public void getReviewsByBookIdTest(){
        Page<Review> reviews = repository.getReviewsByBookId(1L, applyPage());
        Assertions.assertEquals(reviews.getTotalElements(), 1);

    };

    @Test
    public void getBooksByAuthorTest(){
        List<Book> books = repository.getBooksByAuthor("Bor");
        Assertions.assertEquals(books.size(), 0);
    };

    @Test
    public void getBookByGenreTest(){
        List<Book> books = repository.getBookByGenre(Genre.lookup(1));
        Assertions.assertEquals(books.size(), 1);
    };

    @Test
    public void getBooksByAuthorNameTest(){
        Page<Book> bookPage = repository.getBooksByAuthorName("Bor", applyPage());
        Assertions.assertEquals(bookPage.getTotalElements(), 1);
    };
}
