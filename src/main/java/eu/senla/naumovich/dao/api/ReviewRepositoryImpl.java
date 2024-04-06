package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.ReviewRepository;
import eu.senla.naumovich.entities.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {
    final List<Review> reviews = new ArrayList<>();
    @Override
    public List<Review> getAll() {
        return reviews;
    }

    @Override
    public Review getById(Review review) {
        return reviews.stream()
                .filter(r -> r.getId() == review.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Review update(Review review) {
        for(Review r : reviews){
            if(review.getId() == r.getId()){
                r.setText(review.getText());
                return r;
            }
        }
        return null;
    }

    @Override
    public Review create(Review review) {
        reviews.add(review);
        return review;
    }

    @Override
    public void delete(Review review) {
        reviews.removeIf(r -> r.getId() == review.getId());
    }
}
