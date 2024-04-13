package eu.senla.naumovich.services.service.common;

import java.util.List;

public interface AbstractService<T> {
    List<T> getAll();

    T getById(T object);

    T update(T object);

    void create(T object);

    void delete(T object);
}
