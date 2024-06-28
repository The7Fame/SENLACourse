package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.review.ReviewForBookDto;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.util.List;

public interface BookService extends AbstractService<BookDto, BookShortDto> {
    List<ReviewForBookDto> getReviewsByBookId(Long id, int page, int size);

    List<BookShortDto> getBooksByGenreAndTitle(Integer genreId, String bookTitle, int size, int page);

    List<BookShortDto> getPopularBooks(int page, int size);
}
