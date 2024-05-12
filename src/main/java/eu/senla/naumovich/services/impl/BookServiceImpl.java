package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dto.BookDto;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.dto.ReviewForBookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.mapper.ReviewMapper;
import eu.senla.naumovich.services.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
        private final BookRepository bookRepository;
        private final BookMapper bookMapper;
        private final ReviewMapper reviewMapper;

        public List<BookDto> getAll(int size, int page) {
                List<Book> books = bookRepository.getAll(size, page);
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

        @Override
        public List<ReviewForBookDto> getReviewsByBookId(Long id) {
                return reviewMapper.toReviewForBookDtoList(bookRepository.getReviewsByBookId(id));
        }

        @Override
        public List<BookDto> getBooksByGenreAndTitle(Integer genreId, String bookTitle, int size, int page) {
                if(genreId == 0 && bookTitle.isEmpty()){
                        return getAll(size, page);
                }
                if(genreId == 0){
                        return bookMapper.toDtoList(bookRepository.getBookByName(bookTitle, size, page));
                }
                return bookMapper.toDtoList(bookRepository.getBooksByGenreAndTitle(genreId, bookTitle, size, page));
        }
}
