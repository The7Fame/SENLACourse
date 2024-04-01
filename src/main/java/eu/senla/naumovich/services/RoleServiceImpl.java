package eu.senla.naumovich.services;

import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.entities.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<RoleDto> getAll() {
        List<RoleDto> rolesDto = new ArrayList<>();
        List<Role> roles = roleRepository.getAll();
        for(Role role : roles){
            rolesDto.add(modelMapper.map(role, RoleDto.class));
        }
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
