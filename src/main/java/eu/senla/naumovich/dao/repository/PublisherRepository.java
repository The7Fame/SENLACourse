package eu.senla.naumovich.dao.repository;

import eu.senla.naumovich.entities.Publisher;

import java.util.List;

public interface PublisherRepository {
    List<Publisher> getAll();

    Publisher getById(Publisher publisher);

    Publisher update(Publisher publisher);

    Publisher create(Publisher publisher);

    void delete(Publisher publisher);
}
