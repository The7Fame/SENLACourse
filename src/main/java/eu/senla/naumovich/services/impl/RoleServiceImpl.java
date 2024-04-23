package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.entities.Role;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.services.mapper.RoleMapper;
import eu.senla.naumovich.services.service.RoleService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> getAll() {
        try {
            List<Role> roles = roleRepository.getAll();
            List<RoleDto> rolesDto = roles.stream()
                    .map(roleMapper::toDto)
                    .collect(Collectors.toList());
            return rolesDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public RoleDto getById(Long id) {
        try {
            return roleMapper.toDto(roleRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public RoleDto update(RoleDto role) {
        try {
            return roleMapper.toDto(roleRepository.update(roleMapper.toEntity(role)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + role.getId());
        }
    }

    @Override
    public void create(RoleDto role) {
        try {
            roleRepository.create(roleMapper.toEntity(role));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Role role = roleRepository.getById(id);
            roleRepository.delete(role);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
