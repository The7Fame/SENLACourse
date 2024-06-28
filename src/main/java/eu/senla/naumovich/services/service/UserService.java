package eu.senla.naumovich.services.service;

import eu.senla.naumovich.dto.user.UserDto;
import eu.senla.naumovich.dto.user.UserReplenishBalanceDto;
import eu.senla.naumovich.dto.user.UserShortDto;
import eu.senla.naumovich.dto.user.UserUpdateDto;
import eu.senla.naumovich.security.SecurityUser;
import eu.senla.naumovich.services.service.common.AbstractService;

public interface UserService extends AbstractService<UserDto, UserShortDto> {
    public UserDto getAuthenticate(SecurityUser securityUser);

    public UserDto replenishUserBalance(SecurityUser securityUser, UserReplenishBalanceDto replenishBalanceDto);

    public UserDto updateUser(SecurityUser securityUser, UserUpdateDto userUpdateDto);
}
