package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.entities.User;
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
        List<User> users = userRepository.getAll();
        List<UserDto> usersDto = users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
        return usersDto;
    }

    @Override
    public UserDto getById(UserDto user) {
        return userMapper.toDto(userRepository.getById(userMapper.toEntity(user)));
    }

    @Override
    public UserDto update(UserDto user) {
        return userMapper.toDto(userRepository.update(userMapper.toEntity(user)));
    }

    @Override
    public UserDto create(UserDto user) {
        return userMapper.toDto(userRepository.create(userMapper.toEntity(user)));
    }

    @Override
    public void delete(UserDto user) {
        userRepository.delete(userMapper.toEntity(user));
    }
}
