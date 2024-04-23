package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.PrivilegeRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.mapper.PrivilegeMapper;
import eu.senla.naumovich.services.impl.PrivilegeServiceImpl;
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
public class PrivilegeServiceTest {
    @Mock
    private PrivilegeMapper mapper;
    @Mock
    PrivilegeRepository repository;
    @InjectMocks
    private PrivilegeServiceImpl service;

    @Test
    public void getAllTest() {
        Privilege privilege = Generator.createPrivilege();
        PrivilegeDto privilegeDto = Generator.createPrivilegeDto();
        List<PrivilegeDto> privilegeDtos = new ArrayList<>();
        List<Privilege> privileges = new ArrayList<>();
        privilegeDtos.add(privilegeDto);
        privileges.add(privilege);
        when(repository.getAll()).thenReturn(privileges);
        when(mapper.toDto(privilege)).thenReturn(privilegeDto);
        List<PrivilegeDto> result = service.getAll();
        Assertions.assertEquals(privilegeDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        Privilege privilege = Generator.createPrivilege();
        PrivilegeDto privilegeDto = Generator.createPrivilegeDto();
        when(repository.getById(1L)).thenReturn(privilege);
        when(mapper.toDto(privilege)).thenReturn(privilegeDto);
        PrivilegeDto result = service.getById(1L);
        Assertions.assertEquals(privilegeDto, result);
        verify(repository).getById(1L);
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
        doNothing().when(repository).create(privilege);
        service.create(privilegeDto);
        verify(repository).create(privilege);
        verify(mapper).toEntity(privilegeDto);
    }

    @Test
    public void deleteTest() {
        Privilege privilege = Generator.createPrivilege();
        when(repository.getById(privilege.getId())).thenReturn(privilege);
        doNothing().when(repository).delete(privilege);
        service.delete(privilege.getId());
        verify(repository).getById(privilege.getId());
        verify(repository).delete(privilege);
    }
}
