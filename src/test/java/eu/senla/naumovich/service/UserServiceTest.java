package eu.senla.naumovich.service;

import eu.senla.naumovich.data.Generator;
import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.dto.user.UserReplenishBalanceDto;
import eu.senla.naumovich.dto.user.UserShortDto;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.mapper.UserMapper;
import eu.senla.naumovich.repositories.UserRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserMapper mapper;
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    void getAllTest() {
        String sort = "id";
        Pageable pageable = PageRequest.of(0, 5, Sort.by(sort));
        User user = new User();
        Page<User> userPage = new PageImpl<>(Collections.singletonList(user));
        when(repository.findAll(pageable)).thenReturn(userPage);
        when(mapper.toDtoList(anyList())).thenReturn(Collections.singletonList(UserShortDto.builder().build()));
        List<UserShortDto> result = service.getAll(1, 5, sort);
        assertNotNull(result);
        assertFalse(result.isEmpty());
        verify(repository).findAll(pageable);
        verify(mapper).toDtoList(anyList());
    }

    @Test
    void getByIdTest() {
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
    void updateTest() {
        User user = Generator.createUser();
        UserDto userDto = Generator.createUserDto();
        when(mapper.toEntity(userDto)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(userDto);
        UserDto result = service.update(userDto);
        Assertions.assertEquals(userDto, result);
        verify(repository).save(user);
        verify(mapper).toEntity(userDto);
        verify(mapper).toDto(user);
    }

    @Test
    void createTest() {
        User user = Generator.createUser();
        UserDto userDto = Generator.createUserDto();
        when(mapper.toEntity(userDto)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(mapper.toDto(user)).thenReturn(userDto);
        UserDto result = service.create(userDto);
        Assertions.assertEquals(userDto, result);
        verify(repository).save(user);
        verify(mapper).toEntity(userDto);
        verify(mapper).toDto(user);
    }

    @Test
    void deleteTest() {
        User user = Generator.createUser();
        doNothing().when(repository).deleteById(user.getId());
        service.delete(user.getId());
        verify(repository).deleteById(user.getId());
    }

    @Test
    void authenticateTest() {
        User user = Generator.createUser();
        SecurityUser securityUser = (SecurityUser) SecurityUser.fromUser(user);
        UserDto expectedUserDto = Generator.createUserDto();
        when(repository.findById(securityUser.getId())).thenReturn(Optional.of(new User()));
        when(mapper.toDto(any(User.class))).thenReturn(expectedUserDto);
        UserDto actualUserDto = service.getAuthenticate(securityUser);
        Assertions.assertEquals(expectedUserDto, actualUserDto);
    }
}
