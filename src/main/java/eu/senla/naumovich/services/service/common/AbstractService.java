package eu.senla.naumovich.services.service.common;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public interface AbstractService<T> {
    List<T> getAll(int page, int size);

    T getById(Long id);
    @Transactional
    T update(T object);

    @Transactional
    T create(T object);

    @Transactional
    void delete(Long id);
}
