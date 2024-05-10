package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.dto.ReviewDto;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.entities.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.senla.naumovich.entities.Review;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl extends AbstractDao<Long, Book> implements BookRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }

    @Override
    public Book getBookByName(String bookName) {
        String jbqlQuery = "select b from Book b where b.title = :bookName";
        TypedQuery<Book> query = entityManager.createQuery(jbqlQuery, Book.class);
        query.setParameter("bookName", bookName);
        return query.getSingleResult();
    };

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Join<Book, Author> authorJoin = bookRoot.join("author");
        criteriaQuery.select(bookRoot).where(criteriaBuilder.equal(authorJoin.get("name"), authorName));
        return entityManager.createQuery(criteriaQuery).getResultList();
    };

    @Override
    public Book getBookByIdGraph(Integer id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("graph.Book.associations");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", entityGraph);
        Book book = entityManager.find(Book.class, id, hints);
        return book;
    }

    @Override
    public List<Review> getReviewsByBookId(Long id) {
        String jbqlQuery = "select r from Review r where r.book.id = :id";
        TypedQuery <Review> query = entityManager.createQuery(jbqlQuery, Review.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
