package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.dto.ReviewForBookDto;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.util.List;

public interface BookService extends AbstractService<BookDto> {
    List<ReviewForBookDto> getReviewsByBookId(Long id);
}
