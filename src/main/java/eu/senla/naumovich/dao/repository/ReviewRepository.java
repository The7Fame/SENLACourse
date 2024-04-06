package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Review;

import java.util.List;

public interface ReviewRepository {
    List<Review> getAll();

    Review getById(Review review);

    Review update(Review review);

    Review create(Review review);

    void delete(Review review);
}
