package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.mapper.PrivilegeMapper;
import eu.senla.naumovich.services.impl.PrivilegeServiceImpl;
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
public class PrivilegeServiceTest {
    @Mock
    private PrivilegeMapper mapper;
    @Mock
    PrivilegeRepository repository;
    @InjectMocks
    private PrivilegeServiceImpl service;

    @Test
    public void getAllTest() {
        when(repository.getAll(1,2)).thenReturn(Collections.emptyList());
        List<PrivilegeDto> result = service.getAll(1,2);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void getByIdTest() {
        Privilege privilege = Generator.createPrivilege();
        PrivilegeDto privilegeDto = Generator.createPrivilegeDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(privilege));
        when(mapper.toDto(privilege)).thenReturn(privilegeDto);
        PrivilegeDto result = service.getById(1L);
        Assertions.assertEquals(privilegeDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(privilege);
    }

    @Test
    public void updateTest() {
        Privilege privilege = Generator.createPrivilege();
        PrivilegeDto privilegeDto = Generator.createPrivilegeDto();
        when(mapper.toEntity(privilegeDto)).thenReturn(privilege);
        when(repository.update(privilege)).thenReturn(privilege);
        when(mapper.toDto(privilege)).thenReturn(privilegeDto);
        PrivilegeDto result = service.update(privilegeDto);
        Assertions.assertEquals(privilegeDto, result);
        verify(repository).update(privilege);
        verify(mapper).toEntity(privilegeDto);
        verify(mapper).toDto(privilege);
    }

    @Test
    public void createTest() {
        Privilege privilege = Generator.createPrivilege();
        PrivilegeDto privilegeDto = Generator.createPrivilegeDto();
        when(mapper.toEntity(privilegeDto)).thenReturn(privilege);
        when(repository.create(privilege)).thenReturn(privilege);
        when(mapper.toDto(privilege)).thenReturn(privilegeDto);
        PrivilegeDto result = service.create(privilegeDto);
        Assertions.assertEquals(privilegeDto, result);
        verify(repository).create(privilege);
        verify(mapper).toEntity(privilegeDto);
        verify(mapper).toDto(privilege);
    }

    @Test
    public void deleteTest() {
        Privilege privilege = Generator.createPrivilege();
        doNothing().when(repository).deleteById(privilege.getId());
        service.delete(privilege.getId());
        verify(repository).deleteById(privilege.getId());
    }
}
