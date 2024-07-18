package eu.senla.naumovich.mapper.common;

import java.util.List;

public interface InterfaceMapper<E, D, S> {
    D toDto(E entity);
    E toEntity(D dto);

    List<S> toDtoList(List<E> entityList);
    List<E> toEntityList(List<S> dtoList);
}
