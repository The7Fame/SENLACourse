package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.GenreRepository;
import eu.senla.naumovich.entities.Genre;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {
    final List<Genre> genres = new ArrayList<>();
    @Override
    public List<Genre> getAll() {
        return genres;
    }

    @Override
    public Genre getById(Genre genre) {
        return genres.stream()
                .filter(g -> g.getId() == genre.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Genre update(Genre genre) {
        for(Genre g : genres){
            if(genre.getId() == g.getId()){
                g.setGenreName(genre.getGenreName());
                return g;
            }
        }
        return null;
    }

    @Override
    public Genre create(Genre genre) {
        genres.add(genre);
        return genre;
    }

    @Override
    public void delete(Genre genre) {
        genres.removeIf(g -> g.getId() == genre.getId());
    }
}
