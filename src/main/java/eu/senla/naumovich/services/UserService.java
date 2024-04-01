package eu.senla.naumovich.services;

import eu.senla.naumovich.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(UserDto user);

    UserDto update(UserDto user);

    UserDto create(UserDto user);

    void delete(UserDto user);
}
