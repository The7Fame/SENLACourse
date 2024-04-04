package eu.senla.naumovich.dao.api;

import eu.senla.naumovich.dao.repository.AuthorRepository;
import eu.senla.naumovich.entities.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    final List<Author> authors = new ArrayList<>();

    @Override
    public List<Author> getAll() {
        return authors;
    }

    @Override
    public Author getById(Author author) {
        return authors.stream()
                .filter(a -> a.getId() == author.getId())
                .findFirst()
                .orElse(null);
    }

    @Override
    public Author update(Author author) {
        for(Author a : authors){
            if(author.getId() == a.getId()){
                a.setSurname(author.getSurname());
                return a;
            }
        }
        return null;
    }

    @Override
    public Author create(Author author) {
        authors.add(author);
        return author;
    }

    @Override
    public void delete(Author author) {
        authors.removeIf(a -> a.getId() == author.getId());
    }
}
