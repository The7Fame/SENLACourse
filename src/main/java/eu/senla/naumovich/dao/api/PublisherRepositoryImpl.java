package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.entities.Publisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository {
    final List<Publisher> publishers = new ArrayList<>();
    @Override
    public List<Publisher> getAll() {
        return publishers;
    }

    @Override
    public Publisher getById(Publisher publisher) {
        return publishers.stream()
                .filter(p -> p.getId() == publisher.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Publisher update(Publisher publisher) {
        for(Publisher p : publishers){
            if(publisher.getId() == p.getId()){
                p.setPublisherName(publisher.getPublisherName());
                return p;
            }
        }
        return null;
    }

    @Override
    public Publisher create(Publisher publisher) {
        publishers.add(publisher);
        return publisher;
    }

    @Override
    public void delete(Publisher publisher) {
        publishers.removeIf(p -> p.getId() == publisher.getId());
    }
}
