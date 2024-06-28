package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.privilege.PrivilegeDto;
import eu.senla.naumovich.entities.Privilege;
import eu.senla.naumovich.mapper.PrivilegeMapper;
import eu.senla.naumovich.repositories.PrivilegeRepository;
import eu.senla.naumovich.services.impl.PrivilegeServiceImpl;
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
public class PrivilegeServiceTest {
    @Mock
    private PrivilegeMapper mapper;
    @Mock
    PrivilegeRepository repository;
    @InjectMocks
    private PrivilegeServiceImpl service;

    @Test
    public void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        Privilege privilege = new Privilege();
        Page<Privilege> privilegePage = new PageImpl<>(Collections.singletonList(privilege));
        when(repository.findAll(pageable)).thenReturn(privilegePage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(PrivilegeDto.builder().build()));
        List<PrivilegeDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
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
        when(repository.save(privilege)).thenReturn(privilege);
        when(mapper.toDto(privilege)).thenReturn(privilegeDto);
        PrivilegeDto result = service.update(privilegeDto);
        Assertions.assertEquals(privilegeDto, result);
        verify(repository).save(privilege);
        verify(mapper).toEntity(privilegeDto);
        verify(mapper).toDto(privilege);
    }

    @Test
    public void createTest() {
        Privilege privilege = Generator.createPrivilege();
        PrivilegeDto privilegeDto = Generator.createPrivilegeDto();
        when(mapper.toEntity(privilegeDto)).thenReturn(privilege);
        when(repository.save(privilege)).thenReturn(privilege);
        when(mapper.toDto(privilege)).thenReturn(privilegeDto);
        PrivilegeDto result = service.create(privilegeDto);
        Assertions.assertEquals(privilegeDto, result);
        verify(repository).save(privilege);
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
