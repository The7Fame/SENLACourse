package eu.senla.naumovich.controllers.common;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CRUDInterface<T, S> {
    ResponseEntity<List<S>> getAll(int page, int size, String sort);

    ResponseEntity<T> getById(Long id);

    ResponseEntity<?> update(T object);

    ResponseEntity<?> create(T object);

    ResponseEntity<?> delete(Long id);
}
