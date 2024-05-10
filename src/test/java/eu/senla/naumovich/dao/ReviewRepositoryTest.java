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
        Assertions.assertTrue(repository.findById(review.getId()).isPresent());
        Assertions.assertEquals(review, repository.findById(review.getId()).get());
    }

    @Test
    public void updateRecord() {
        Review review = Generator.updateReview();
        repository.update(review);
        Assertions.assertTrue(repository.findById(review.getId()).isPresent());
        Assertions.assertEquals(review, repository.findById(review.getId()).get());
    }

    @Test
    public void deleteTest() {
        Review review = Generator.createReview();
        repository.create(review);
        repository.deleteById(review.getId());
        Assertions.assertEquals(repository.getAll(1,2).size(), 2);
    }
}
