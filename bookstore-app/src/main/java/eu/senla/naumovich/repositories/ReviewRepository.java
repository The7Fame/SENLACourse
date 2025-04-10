package eu.senla.naumovich.repositories;

import eu.senla.naumovich.entities.Review;
import eu.senla.naumovich.repositories.projection.ReviewView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.user.id = :userId and r.id = :reviewId")
    Optional<ReviewView> getByUserAndReviewById(@Param("userId") long userId, @Param("reviewId") long reviewId);
}
