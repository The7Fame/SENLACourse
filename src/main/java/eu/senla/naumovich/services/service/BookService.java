package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.review.ReviewForBookDto;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.util.List;

public interface BookService extends AbstractService<BookDto> {
    List<ReviewForBookDto> getReviewsByBookId(Long id);
    List<BookDto> getBooksByGenreAndTitle(Integer genreId, String bookTitle, int size, int page);
    List<BookDto> getPopularBooks(int page, int size);
}
