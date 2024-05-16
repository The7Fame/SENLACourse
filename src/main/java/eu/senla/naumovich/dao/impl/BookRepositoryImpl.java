package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.BookRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Author;
import eu.senla.naumovich.entities.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.entities.enums.Genre;
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
    public List<Book> getBookByName(String bookTitle, int page, int size) {
        String jbqlQuery = "select b from Book b where b.title like :bookName";
        TypedQuery<Book> query = entityManager.createQuery(jbqlQuery, Book.class);
        query.setParameter("bookName", "%" + bookTitle + "%");
        applyPagination(query, page, size);
        return query.getResultList();
    }

    public List<Book> getBookByGenre(Integer genreId){
        Genre genre = Genre.lookup(genreId);
        String jbqlQuery = "select b from Book b where b.genre = :genre";
        TypedQuery <Book> query = entityManager.createQuery(jbqlQuery, Book.class);
        query.setParameter("genre", genre);
        return query.getResultList();
    }

    @Override
    public List<Book> getPopularBooks(int page, int size) {
        String jpqlQuery = "select c.book from Cart c join c.book b group by b.id order by count(b) desc";
        TypedQuery<Book> query = entityManager.createQuery(jpqlQuery, Book.class);
        applyPagination(query, page, size);
        return query.getResultList();
    }

    @Override
    public List<Book> getBooksByAuthor(String authorName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> bookRoot = criteriaQuery.from(Book.class);
        Join<Book, Author> authorJoin = bookRoot.join("authors");
        criteriaQuery.select(bookRoot).where(criteriaBuilder.equal(authorJoin.get("name"), authorName));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Book> getBooksByGenreAndTitle(Integer genreId, String bookTitle, int page, int size) {
        Genre genre = Genre.lookup(genreId);
        String jbqlQuery = "select b from Book b where b.genre = :genre and b.title like :bookName";
        TypedQuery <Book> query = entityManager.createQuery(jbqlQuery, Book.class);
        query.setParameter("genre", genre);
        query.setParameter("bookName", "%" + bookTitle + "%");
        applyPagination(query, page, size);
        return query.getResultList();
    }

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
