package eu.senla.naumovich.dao.repository.common;

import java.util.List;

public interface AbstractRepository<T> {
    List<T> getAll();

    T getById(T object);

    T update(T object);

    T create(T object);

    void delete(T object);
}
