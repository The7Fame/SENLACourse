package eu.senla.naumovich.services.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface AbstractService<T> {
    List<T> getAll();

    T getById(Long id);

    T update(T object);

    void create(T object);

    void delete(Long id);
}
