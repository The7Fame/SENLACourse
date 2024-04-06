package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.entities.Role;
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
    private final ModelMapper modelMapper;

    @Override
    public List<RoleDto> getAll() {
        List<Role> roles = roleRepository.getAll();
        List<RoleDto> rolesDto = roles.stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
        return rolesDto;
    }

    @Override
    public RoleDto getById(RoleDto role) {
        return modelMapper.map(roleRepository.getById(modelMapper.map(role, Role.class)), RoleDto.class);
    }

    @Override
    public RoleDto update(RoleDto role) {
        return modelMapper.map(roleRepository.update(modelMapper.map(role, Role.class)), RoleDto.class);
    }

    @Override
    public RoleDto create(RoleDto role) {
        return modelMapper.map(roleRepository.create(modelMapper.map(role, Role.class)), RoleDto.class);
    }

    @Override
    public void delete(RoleDto role) {
        roleRepository.delete(modelMapper.map(role, Role.class));
    }
}
