package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.RoleRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.RoleDto;
import eu.senla.naumovich.entities.Role;
import eu.senla.naumovich.mapper.RoleMapper;
import eu.senla.naumovich.services.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {
    @Mock
    private RoleMapper mapper;
    @Mock
    RoleRepository repository;
    @InjectMocks
    private RoleServiceImpl service;

    @Test
    public void getAllTest() {
        Role role = Generator.createRole();
        RoleDto roleDto = Generator.createRoleDto();
        List<RoleDto> roleDtos = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        roleDtos.add(roleDto);
        roles.add(role);
        when(repository.getAll()).thenReturn(roles);
        when(mapper.toDto(role)).thenReturn(roleDto);
        List<RoleDto> result = service.getAll();
        Assertions.assertEquals(roleDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        Role role = Generator.createRole();
        RoleDto roleDto = Generator.createRoleDto();
        when(repository.getById(1L)).thenReturn(role);
        when(mapper.toDto(role)).thenReturn(roleDto);
        RoleDto result = service.getById(1L);
        Assertions.assertEquals(roleDto, result);
        verify(repository).getById(1L);
        verify(mapper).toDto(role);
    }

    @Test
    public void updateTest() {
        Role role = Generator.createRole();
        RoleDto roleDto = Generator.createRoleDto();
        when(mapper.toEntity(roleDto)).thenReturn(role);
        when(repository.update(role)).thenReturn(role);
        when(mapper.toDto(role)).thenReturn(roleDto);
        RoleDto result = service.update(roleDto);
        Assertions.assertEquals(roleDto, result);
        verify(repository).update(role);
        verify(mapper).toEntity(roleDto);
        verify(mapper).toDto(role);
    }

    @Test
    public void createTest() {
        Role role = Generator.createRole();
        RoleDto roleDto = Generator.createRoleDto();
        when(mapper.toEntity(roleDto)).thenReturn(role);
        doNothing().when(repository).create(role);
        service.create(roleDto);
        verify(repository).create(role);
        verify(mapper).toEntity(roleDto);
    }

    @Test
    public void deleteTest() {
        Role role = Generator.createRole();
        when(repository.getById(role.getId())).thenReturn(role);
        doNothing().when(repository).delete(role);
        service.delete(role.getId());
        verify(repository).getById(role.getId());
        verify(repository).delete(role);
    }
}
