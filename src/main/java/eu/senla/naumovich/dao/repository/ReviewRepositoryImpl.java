package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Review;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository{
    final List<Review> reviews = new ArrayList<>();
    @Override
    public List<Review> getAll() {
        return reviews;
    }

    @Override
    public Review getById(Review review) {
        for(Review r : reviews){
            if(r.getId() == review.getId()){
                return r;
            }
        }
        return null;
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
        for (int i = 0; i < reviews.size(); i++) {
            if (review.getId() == reviews.get(i).getId()) {
                reviews.remove(i);
            }
        }
    }
}
