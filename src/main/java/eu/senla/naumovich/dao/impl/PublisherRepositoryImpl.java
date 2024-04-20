package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.PublisherRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Publisher;
import org.springframework.stereotype.Repository;

@Repository
public class PublisherRepositoryImpl extends AbstractDao<Long, Publisher> implements PublisherRepository {
    @Override
    protected Class<Publisher> getEntityClass() {
        return Publisher.class;
    }
}
