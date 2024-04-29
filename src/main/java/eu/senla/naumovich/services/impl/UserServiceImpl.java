package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.exceptions.NoRecords;
import eu.senla.naumovich.mapper.UserMapper;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
        private final UserMapper userMapper;

        @Override
        public List<UserDto> getAll() {
                List<User> users = userRepository.getAll();
                return userMapper.toDtoList(users);
        }

        @Override
        public UserDto getById(Long id) {
                return userMapper.toDto(userRepository.findById(id)
                                .orElseThrow(() -> new NoRecords("No record with such ID " + id)));

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
        public void delete(Long id) {
                userRepository.deleteById(id);
        }
}
