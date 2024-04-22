package eu.senla.naumovich.dao.repository.common;

import java.util.List;

public interface AbstractRepository<T, K> {
    List<T> getAll();

    T getById(K id);

    T update(T object);

    void create(T object);

    void delete(T object);
}
