package pl.sda.service;

import pl.sda.dto.PasswordDto;
import pl.sda.dto.UserDto;
import pl.sda.exception.EmptyUsernameException;
import pl.sda.exception.InvalidPasswordException;
import pl.sda.exception.WrongPasswordException;
import pl.sda.model.User;

import java.util.List;

public interface UserService {

    boolean existsByUsername(String username) throws EmptyUsernameException;

    void save(User user);

    List<UserDto> findAll();

    void deleteByUsername(String username);

    void save(UserDto userDto);

    void changePassword(String username, PasswordDto passwordDto) throws WrongPasswordException, InvalidPasswordException;

}
