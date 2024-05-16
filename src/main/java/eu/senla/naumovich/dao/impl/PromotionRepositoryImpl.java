package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dao.repository.PromotionRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Promotion;
import eu.senla.naumovich.entities.enums.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class PromotionRepositoryImpl extends AbstractDao<Long, Promotion> implements PromotionRepository {
    private final BookRepository bookRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    protected Class<Promotion> getEntityClass() {
        return Promotion.class;
    }

    @Transactional
    public Promotion createPromotionByGenre(Integer genreId, BigDecimal percent, String promotionName) {
        List<Book> books = bookRepository.getBookByGenre(genreId);
        System.out.println(books);
        return create(Promotion.builder()
                .books(books)
                .percent(percent)
                .promotionName(promotionName)
                .build());
    }

    @Transactional
    public Promotion createPromotionByAuthor(String authorName, BigDecimal percent, String promotionName) {
        List<Book> books = bookRepository.getBooksByAuthor(authorName);
        return create(Promotion.builder()
                .books(books)
                .percent(percent)
                .promotionName(promotionName)
                .build());
    }
}
