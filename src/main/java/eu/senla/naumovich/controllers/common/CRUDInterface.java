package eu.senla.naumovich.controllers.common;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CRUDInterface<T> {
    ResponseEntity<List<T>> getAll();

    ResponseEntity<T> getById(Long id);

    ResponseEntity<?> update(T object);

    ResponseEntity<?> create(T object);

    ResponseEntity<?> delete(Long id);
}
