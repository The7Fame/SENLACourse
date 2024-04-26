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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        when(repository.getAll()).thenReturn(Collections.emptyList());
        List<RoleDto> result = service.getAll();
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Role role = Generator.createRole();
        RoleDto roleDto = Generator.createRoleDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(role));
        when(mapper.toDto(role)).thenReturn(roleDto);
        RoleDto result = service.getById(1L);
        Assertions.assertEquals(roleDto, result);
        verify(repository).findById(1L);
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
        when(repository.create(role)).thenReturn(role);
        when(mapper.toDto(role)).thenReturn(roleDto);
        RoleDto result = service.create(roleDto);
        Assertions.assertEquals(roleDto, result);
        verify(repository).create(role);
        verify(mapper).toEntity(roleDto);
        verify(mapper).toDto(role);
    }

    @Test
    public void deleteTest() {
        Role role = Generator.createRole();
        doNothing().when(repository).deleteById(role.getId());
        service.delete(role.getId());
        verify(repository).deleteById(role.getId());
    }
}
