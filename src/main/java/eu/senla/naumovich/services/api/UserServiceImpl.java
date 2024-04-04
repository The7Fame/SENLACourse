package eu.senla.naumovich.services.api;

import eu.senla.naumovich.dao.repository.UserRepository;
import eu.senla.naumovich.dto.UserDto;
import eu.senla.naumovich.entities.User;
import eu.senla.naumovich.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserDto> getAll() {
        List<UserDto> usersDto = new ArrayList<>();
        List<User> users = userRepository.getAll();
        for(User user : users){
            usersDto.add(modelMapper.map(user, UserDto.class));
        }
        return usersDto;
    }

    @Override
    public UserDto getById(UserDto user) {
        return modelMapper.map(userRepository.getById(modelMapper.map(user, User.class)), UserDto.class);
    }

    @Override
    public UserDto update(UserDto user) {
        return modelMapper.map(userRepository.update(modelMapper.map(user, User.class)), UserDto.class);
    }

    @Override
    public UserDto create(UserDto user) {
        return modelMapper.map(userRepository.create(modelMapper.map(user, User.class)), UserDto.class);
    }

    @Override
    public void delete(UserDto user) {
        userRepository.delete(modelMapper.map(user, User.class));
    }
}
