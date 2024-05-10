package eu.senla.naumovich.dao.repository.common;

import java.util.List;
import java.util.Optional;

public interface AbstractRepository<T, K> {
    List<T> getAll(int page, int size);

    Optional<T> findById(K id);

    T update(T object);

    T create(T object);

    void deleteById(K id);
}
