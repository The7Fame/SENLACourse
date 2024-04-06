package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getAll();

    RoleDto getById(RoleDto role);

    RoleDto update(RoleDto role);

    RoleDto create(RoleDto role);

    void delete(RoleDto role);
}
