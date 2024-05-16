package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.mapper.UserMapper;
import eu.senla.naumovich.services.impl.UserServiceImpl;
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
public class UserServiceTest {
    @Mock
    private UserMapper mapper;
    @Mock
    UserRepository repository;
    @InjectMocks
    private UserServiceImpl service;

    @Test
    public void getAllTest() {
        when(repository.getAll(1,2)).thenReturn(Collections.emptyList());
        List<UserDto> result = service.getAll(1,2);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void getByIdTest() {
        User user = Generator.createUser();
        UserDto userDto = Generator.createUserDto();
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(mapper.toDto(user)).thenReturn(userDto);
        UserDto result = service.getById(1L);
        Assertions.assertEquals(userDto, result);
        verify(repository).findById(1L);
        verify(mapper).toDto(user);
    }

    @Test
    public void updateTest() {
        User user = Generator.createUser();
        UserDto userDto = Generator.createUserDto();
        when(mapper.toEntity(userDto)).thenReturn(user);
        when(repository.update(user)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(userDto);
        UserDto result = service.update(userDto);
        Assertions.assertEquals(userDto, result);
        verify(repository).update(user);
        verify(mapper).toEntity(userDto);
        verify(mapper).toDto(user);
    }

    @Test
    public void createTest() {
        User user = Generator.createUser();
        UserDto userDto = Generator.createUserDto();
        when(mapper.toEntity(userDto)).thenReturn(user);
        when(repository.create(user)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(userDto);
        UserDto result = service.create(userDto);
        Assertions.assertEquals(userDto, result);
        verify(repository).create(user);
        verify(mapper).toEntity(userDto);
        verify(mapper).toDto(user);
    }

    @Test
    public void deleteTest() {
        User user = Generator.createUser();
        doNothing().when(repository).deleteById(user.getId());
        service.delete(user.getId());
        verify(repository).deleteById(user.getId());
    }
}
