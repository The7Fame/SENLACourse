package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.role.RoleDto;
import eu.senla.naumovich.dto.role.RoleShortDto;
import eu.senla.naumovich.entities.Role;
import eu.senla.naumovich.mapper.RoleMapper;
import eu.senla.naumovich.repositories.RoleRepository;
import eu.senla.naumovich.services.impl.RoleServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {
    @Mock
    private RoleMapper mapper;
    @Mock
    private RoleRepository repository;
    @InjectMocks
    private RoleServiceImpl service;

    @Test
    void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Role role = new Role();
        Page<Role> rolePage = new PageImpl<>(Collections.singletonList(role));
        when(repository.findAll(pageable)).thenReturn(rolePage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(RoleShortDto.builder().build()));
        List<RoleShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    void getByIdTest() {
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
    void updateTest() {
        Role role = Generator.createRole();
        RoleDto roleDto = Generator.createRoleDto();
        when(mapper.toEntity(roleDto)).thenReturn(role);
        when(repository.save(role)).thenReturn(role);
        when(mapper.toDto(role)).thenReturn(roleDto);
        RoleDto result = service.update(roleDto);
        Assertions.assertEquals(roleDto, result);
        verify(repository).save(role);
        verify(mapper).toEntity(roleDto);
        verify(mapper).toDto(role);
    }

    @Test
    void createTest() {
        Role role = Generator.createRole();
        RoleDto roleDto = Generator.createRoleDto();
        when(mapper.toEntity(roleDto)).thenReturn(role);
        when(repository.save(role)).thenReturn(role);
        when(mapper.toDto(role)).thenReturn(roleDto);
        RoleDto result = service.create(roleDto);
        Assertions.assertEquals(roleDto, result);
        verify(repository).save(role);
        verify(mapper).toEntity(roleDto);
        verify(mapper).toDto(role);
    }

    @Test
    void deleteTest() {
        Role role = Generator.createRole();
        doNothing().when(repository).deleteById(role.getId());
        service.delete(role.getId());
        verify(repository).deleteById(role.getId());
    }
}
