package eu.senla.naumovich.dao;

import eu.senla.naumovich.config.DaoConfig;
import eu.senla.naumovich.config.TestConfig;
import eu.senla.naumovich.dao.repository.ReviewRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.entities.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class, DaoConfig.class })
public class ReviewRepositoryTest {
    @Autowired
    ReviewRepository repository;

    @Test
    public void createRecord() {
        Review review = Generator.createReview();
        repository.create(review);
        Assertions.assertEquals(review, repository.getById(review.getId()));
    }

    @Test
    public void updateRecord() {
        Review review = Generator.updateReview();
        repository.update(review);
        Assertions.assertEquals(review, repository.getById(review.getId()));
    }

    @Test
    public void deleteTest() {
        Review review = Generator.createReview();
        repository.delete(review);
        Assertions.assertNull(repository.getById(review.getId()));
    }
}
