package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.author.AuthorDto;
import eu.senla.naumovich.dto.author.AuthorShortDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.mapper.AuthorMapper;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.repositories.AuthorRepository;
import eu.senla.naumovich.repositories.BookRepository;
import eu.senla.naumovich.services.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorShortDto> getAll(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        Page<Author> authorPage = authorRepository.findAll(pageable);
        return authorMapper.toDtoList(authorPage.getContent());
    }

    @Override
    public AuthorDto getById(Long id) {
        return authorMapper
                .toDto(authorRepository.findById(id)
                        .orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
    }

    @Override
    public AuthorDto update(AuthorDto author) {
        return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(author)));
    }

    @Override
    public AuthorDto create(AuthorDto author) {
        return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(author)));
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<BookShortDto> getAuthorBooks(int page, int size, String authorName) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Book> bookPage = bookRepository.getBooksByAuthorName(authorName, pageable);
        return bookMapper.toDtoList(bookPage.getContent());
    }
}
