package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.services.mapper.UserMapper;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAll() {
        try {
            List<User> users = userRepository.getAll();
            List<UserDto> usersDto = users.stream()
                    .map(userMapper::toDto)
                    .collect(Collectors.toList());
            return usersDto;
        } catch (Exception e) {
            throw new NoRecords("No records");
        }
    }

    @Override
    public UserDto getById(Long id) {
        try {
            return userMapper.toDto(userRepository.getById(id));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }

    @Override
    public UserDto update(UserDto user) {
        try {
            return userMapper.toDto(userRepository.update(userMapper.toEntity(user)));
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + user.getId());
        }
    }

    @Override
    public void create(UserDto user) {
        try {
            userRepository.create(userMapper.toEntity(user));
        } catch (Exception e) {
            throw new NoRecords("Cannot create record");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            User user = userRepository.getById(id);
            userRepository.delete(user);
        } catch (Exception e) {
            throw new NoRecords("No record with such ID " + id);
        }
    }
}
