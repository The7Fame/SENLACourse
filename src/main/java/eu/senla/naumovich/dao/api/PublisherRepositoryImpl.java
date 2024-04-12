package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.entities.Publisher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {

    @Override
    public List<Publisher> getAll() {
        return null;
    }

    @Override
    public Publisher getById(Publisher publisher) {
        return null;
    }

    @Override
    public Publisher update(Publisher publisher) {
        return null;
    }

    @Override
    public Publisher create(Publisher publisher) {
        return null;
    }

    @Override
    public void delete(Publisher publisher) {
    }
}
