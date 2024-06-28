package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.book.BookDto;
import eu.senla.naumovich.dto.book.BookShortDto;
import eu.senla.naumovich.dto.review.ReviewForBookDto;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.entities.enums.Genre;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.exceptions.RecordExistsException;
import eu.senla.naumovich.mapper.BookMapper;
import eu.senla.naumovich.mapper.ReviewMapper;
import eu.senla.naumovich.repositories.BookRepository;
import eu.senla.naumovich.services.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
        private final BookRepository bookRepository;
        private final BookMapper bookMapper;
        private final ReviewMapper reviewMapper;

        public List<BookShortDto> getAll(int page, int size, String sort) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
                Page<Book> bookPage = bookRepository.findAll(pageable);
                return bookMapper.toDtoList(bookPage.getContent());
        }

        @Override
        public BookDto getById(Long id) {
                return bookMapper.toDto(bookRepository.findById(id)
                                .orElseThrow(() -> new NoRecordException("No record with such ID " + id)));
        }

        @Override
        public BookDto update(BookDto book) {
                return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)));
        }

        @Override
        public BookDto create(BookDto book) {
                try {
                        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(book)));
                }catch (Exception e){
                        throw new RecordExistsException("Record is exists");
                }
        }

        @Override
        public void delete(Long id) {
                bookRepository.deleteById(id);
        }

        @Override
        public List<ReviewForBookDto> getReviewsByBookId(Long id, int page, int size) {
                Pageable pageable = PageRequest.of(page - 1, size);
                Page<Review> reviewPage = bookRepository.getReviewsByBookId(id, pageable);
                System.out.println(reviewPage.getContent());
                return reviewMapper
                                .toReviewForBookDtoList(reviewPage.getContent());
        }

        @Override
        public List<BookShortDto> getBooksByGenreAndTitle(Integer genreId, String bookTitle, int page, int size) {
                Pageable pageable = PageRequest.of(page - 1, size);
                if (genreId == 0 && bookTitle.isEmpty()) {
                        return bookMapper.toDtoList(bookRepository.findAll(pageable).getContent());
                }
                if (genreId == 0) {
                        return bookMapper.toDtoList(bookRepository.getBookByName(bookTitle, pageable).getContent());
                }
                Genre genre = Genre.lookup(genreId);
                return bookMapper.toDtoList(
                                bookRepository.getBooksByGenreAndTitle(genre, bookTitle, pageable).getContent());
        }

        @Override
        public List<BookShortDto> getPopularBooks(int page, int size) {
                Pageable pageable = PageRequest.of(page - 1, size);
                Page<Book> bookPage = bookRepository.getPopularBooks(pageable);
                return bookMapper.toDtoList(bookPage.getContent());
        }
}
