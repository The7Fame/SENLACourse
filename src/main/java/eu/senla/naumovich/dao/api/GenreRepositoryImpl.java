package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.GenreRepository;
import eu.senla.naumovich.dao.repository.common.AbstractDao;
import eu.senla.naumovich.entities.Genre;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepositoryImpl extends AbstractDao<Long, Genre> implements GenreRepository {
    @Override
    protected Class<Genre> getEntityClass() {
        return Genre.class;
    }
}
