package eu.senla.naumovich.dao;

import eu.senla.naumovich.dao.common.BaseRepositoryTest;
import eu.senla.naumovich.repositories.ReviewRepository;
import eu.senla.naumovich.repositories.projection.ReviewView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class ReviewRepositoryTest extends BaseRepositoryTest {
    @Autowired
    private ReviewRepository repository;

    @Test
    void getByUserAndReviewById(){
        Optional<ReviewView> review = repository.getByUserAndReviewById(1, 1);
        Assertions.assertTrue(review.isPresent());
    };
}
