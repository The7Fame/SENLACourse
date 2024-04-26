package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.services.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
        private final BookRepository bookRepository;
        private final BookMapper bookMapper;

        public List<BookDto> getAll() {
                List<Book> books = bookRepository.getAll();
                if (books.isEmpty()) {
                        return Collections.emptyList();
                }
                return bookMapper.toDtoList(books);
        }

        @Override
        public BookDto getById(Long id) {
                return bookMapper.toDto(bookRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));
        }

        @Override
        public BookDto update(BookDto book) {
                return bookMapper.toDto(bookRepository.update(bookMapper.toEntity(book)));
        }

        @Override
        public BookDto create(BookDto book) {
                return bookMapper.toDto(bookRepository.create(bookMapper.toEntity(book)));
        }

        @Override
        public void delete(Long id) {
                bookRepository.deleteById(id);

        }
}
