package eu.senla.naumovich.repositories;

import eu.senla.naumovich.entities.Book;
import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.entities.enums.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.title like %:bookTitle%")
    Page<Book> getBookByName(@Param("bookTitle") String bookTitle, Pageable pageable);

    @Query("select b from Review r join r.book b group by b.id order by avg(r.rating) desc")
    Page<Book> getPopularBooks(Pageable pageable);

    @Query("select b from Book b where b.genre = :genre and b.title like %:bookTitle%")
    Page<Book> getBooksByGenreAndTitle(@Param("genre") Genre genre, @Param("bookTitle") String bookTitle,
            Pageable pageable);

    @Query("select r from Review r join r.book b where r.book.id = :id")
    Page<Review> getReviewsByBookId(@Param("id") Long id, Pageable pageable);

    @Query("select b from Book b join b.authors a where a.name = :authorName")
    List<Book> getBooksByAuthor(@Param("authorName") String authorName);

    @Query("select b from Book b where b.genre = :genre")
    List<Book> getBookByGenre(@Param("genre") Genre genre);

    @Query("select b from Book b join b.authors a where a.name like %:authorName%")
    Page<Book> getBooksByAuthorName(@Param("authorName") String authorName, Pageable pageable);
}