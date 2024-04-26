package eu.senla.naumovich.mapper.common;

import java.util.List;

public interface InterfaceMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);

    List<D> toDtoList(List<E> entityList);
    List<E> toEntityList(List<D> dtoList);
}
