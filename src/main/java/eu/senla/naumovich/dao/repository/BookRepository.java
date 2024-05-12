package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.dao.repository.common.AbstractRepository;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Review;

import java.util.List;

public interface BookRepository extends AbstractRepository<Book, Long> {
    public List<Book> getBookByName(String bookTitle, int page, int size);
    public List<Book> getBooksByAuthor(String authorName);
    public List<Book> getBooksByGenreAndTitle(Integer genreId, String bookTitle, int page, int size);
    public Book getBookByIdGraph(Integer id);
    public List<Review> getReviewsByBookId(Long id);
}
