package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.dao.repository.common.AbstractRepository;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Review;

import java.util.List;

public interface BookRepository extends AbstractRepository<Book, Long> {
    public Book getBookByName(String bookName);

    public List<Book> getBooksByAuthor(String authorName);

    public Book getBookByIdGraph(Integer id);
    public List<Review> getReviewsByBookId(Long id);
}
