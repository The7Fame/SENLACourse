package eu.senla.naumovich.services.impl;

import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.dto.user.UserReplenishBalanceDto;
import eu.senla.naumovich.dto.user.UserShortDto;
import eu.senla.naumovich.dto.user.UserUpdateDto;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.exceptions.NoRecordException;
import eu.senla.naumovich.exceptions.NoUserException;
import eu.senla.naumovich.exceptions.RecordExistsException;
import eu.senla.naumovich.mapper.UserMapper;
import eu.senla.naumovich.repositories.UserRepository;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
        private final UserMapper userMapper;
        private final PasswordEncoder passwordEncoder;

        @Override
        public List<UserShortDto> getAll(int page, int size, String sort) {
                Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
                Page<User> userPage = userRepository.findAll(pageable);
                return userMapper.toDtoList(userPage.getContent());
        }

        @Override
        public UserDto getById(Long id) {
                return userMapper.toDto(userRepository.findById(id)
                                .orElseThrow(() -> new NoUserException(id)));
        }

        @Override
        public UserDto update(UserDto user) {
                return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
        }

        @Override
        public UserDto create(UserDto user) {
                try {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        return userMapper.toDto(userRepository.save(userMapper.toEntity(user)));
                } catch (Exception e) {
                        throw new RecordExistsException("Record is exists");
                }

        }

        @Override
        public void delete(Long id) {
                userRepository.deleteById(id);
        }

        @Override
        public UserDto getAuthenticate(SecurityUser securityUser) {
                return getById(securityUser.getId());
        }

        @Transactional
        public UserDto replenishUserBalance(SecurityUser securityUser, UserReplenishBalanceDto replenishBalanceDto) {
                UserDto userDto = getById(securityUser.getId());
                userDto.setBalance(userDto.getBalance().add(replenishBalanceDto.getBalance()));
                return update(userDto);
        }

        @Transactional
        public UserDto updateUser(SecurityUser securityUser, UserUpdateDto userUpdateDto) {
                UserDto userDto = getById(securityUser.getId());
                userDto.setName(userUpdateDto.getName());
                userDto.setSurname(userUpdateDto.getSurname());
                return update(userDto);
        }
}
