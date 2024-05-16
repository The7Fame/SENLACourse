package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.common.AbstractService;

import java.math.BigDecimal;

public interface UserService extends AbstractService<UserDto> {
    public UserDto getAuthenticate(SecurityUser securityUser);
}
