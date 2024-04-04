package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.PrivilegeDto;

import java.util.List;

public interface PrivilegeService {
    List<PrivilegeDto> getAll();

    PrivilegeDto getById(PrivilegeDto privilege);

    PrivilegeDto update(PrivilegeDto privilege);

    PrivilegeDto create(PrivilegeDto privilege);

    void delete(PrivilegeDto privilege);
}
