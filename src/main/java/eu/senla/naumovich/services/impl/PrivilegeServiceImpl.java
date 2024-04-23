package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.PrivilegeMapper;
import eu.senla.naumovich.services.service.PrivilegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;

    @Override
    public List<PrivilegeDto> getAll() {
        try {
            List<Privilege> privileges = privilegeRepository.getAll();
            List<PrivilegeDto> privilegesDto = privileges.stream()
                    .map(privilegeMapper::toDto)
                    .collect(Collectors.toList());
            return privilegesDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public PrivilegeDto getById(Long id) {
        try {
            return privilegeMapper.toDto(privilegeRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public PrivilegeDto update(PrivilegeDto privilege) {
        try {
            return privilegeMapper.toDto(privilegeRepository.update(privilegeMapper.toEntity(privilege)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + privilege.getId());
        }
    }

    @Override
    public void create(PrivilegeDto privilege) {
        try {
            privilegeRepository.create(privilegeMapper.toEntity(privilege));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Privilege privilege = privilegeRepository.getById(id);
            privilegeRepository.delete(privilege);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
