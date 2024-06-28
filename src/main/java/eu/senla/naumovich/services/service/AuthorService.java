package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.dto.author.AuthorShortDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.util.List;

public interface AuthorService extends AbstractService<AuthorDto, AuthorShortDto> {
    List<BookShortDto> getAuthorBooks(int page, int size, String authorName);
}
