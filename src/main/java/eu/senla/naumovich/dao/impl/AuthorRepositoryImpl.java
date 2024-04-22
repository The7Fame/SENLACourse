package eu.senla.naumovich.dao.impl;

import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Author;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl extends AbstractDao<Long, Author> implements AuthorRepository {
    @Override
    protected Class<Author> getEntityClass() {
        return Author.class;
    }
}
