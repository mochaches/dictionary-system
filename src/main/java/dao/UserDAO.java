package dao;

import dto.UserDto;

import java.util.List;

public interface UserDAO {
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    void updateUserById(UserDto pageDto);
    int deleteUserById(Long id);
    int insertUser(UserDto pageDto);
}
