package service;

import dao.UserDAO;
import dto.UserDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public UserDto getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUserById(UserDto pageDto) {
        userDAO.updateUserById(pageDto);
    }

    @Override
    public int deleteUserById(Long id) {
        return userDAO.deleteUserById(id);
    }

    @Override
    public int insertUser(UserDto pageDto) {
        return userDAO.insertUser(pageDto);
    }
}
