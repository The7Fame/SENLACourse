package eu.senla.naumovich.service;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.mapper.UserMapper;
import eu.senla.naumovich.services.impl.UserServiceImpl;
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
public class UserServiceTest {
    @Mock
    private UserMapper mapper;
    @Mock
    UserRepository repository;
    @InjectMocks
    private UserServiceImpl service;

    @Test
    public void getAllTest() {
        User user = Generator.createUser();
        UserDto userDto = Generator.createUserDto();
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = new ArrayList<>();
        userDtos.add(userDto);
        users.add(user);
        when(repository.getAll()).thenReturn(users);
        when(mapper.toDto(user)).thenReturn(userDto);
        List<UserDto> result = service.getAll();
        Assertions.assertEquals(userDtos, result);
        verify(repository).getAll();
    }

    @Test
    public void getByIdTest() {
        User user = Generator.createUser();
        UserDto userDto = Generator.createUserDto();
        when(repository.getById(1L)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(userDto);
        UserDto result = service.getById(1L);
        Assertions.assertEquals(userDto, result);
        verify(repository).getById(1L);
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
        doNothing().when(repository).create(user);
        service.create(userDto);
        verify(repository).create(user);
        verify(mapper).toEntity(userDto);
    }

    @Test
    public void deleteTest() {
        User user = Generator.createUser();
        when(repository.getById(user.getId())).thenReturn(user);
        doNothing().when(repository).delete(user);
        service.delete(user.getId());
        verify(repository).getById(user.getId());
        verify(repository).delete(user);
    }
}
