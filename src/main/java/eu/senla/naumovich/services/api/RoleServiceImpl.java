package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.entities.Role;
import eu.senla.naumovich.services.mapper.RoleMapper;
import eu.senla.naumovich.services.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.getAll();
        List<RoleDto> rolesDto = roles.stream()
                .map(roleMapper::toDto)
                .collect(Collectors.toList());
        return rolesDto;
    }

    @Override
    public RoleDto getById(RoleDto role) {
        return roleMapper.toDto(roleRepository.getById(roleMapper.toEntity(role)));
    }

    @Override
    public RoleDto update(RoleDto role) {
        return roleMapper.toDto(roleRepository.update(roleMapper.toEntity(role)));
    }

    @Override
    public RoleDto create(RoleDto role) {
        return roleMapper.toDto(roleRepository.create(roleMapper.toEntity(role)));
    }

    @Override
    public void delete(RoleDto role) {
        roleRepository.delete(roleMapper.toEntity(role));
    }
}
