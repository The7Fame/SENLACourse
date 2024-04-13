package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.ReviewRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl extends AbstractDao<Long, Review> implements ReviewRepository {
    @Override
    protected Class<Review> getEntityClass() {
        return Review.class;
    }
}
