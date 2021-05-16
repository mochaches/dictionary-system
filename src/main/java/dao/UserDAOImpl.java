package dao;

import dto.UserDto;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
public class UserDAOImpl implements UserDAO {

    @Override
    public UserDto getUserById(Long id) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")
        ) {
            statement.setLong(1, id);
            log.debug("get User by id {}", id);
            ResultSet rs = statement.executeQuery();
            //перебираем результаты
            if (rs.next()) {
                return new UserDto(rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getTimestamp(7));
            } else {
                log.warn("No user found for id = {}", id);
            }
        } catch (SQLException sqlException) {
            throw new NoSuchElementException("Проблема с обращением к базе данных");
        }
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
        ) {
            log.debug("get all users");
            ResultSet rs = statement.executeQuery();
            List<UserDto> users = new ArrayList<>();
            while (rs.next()) {
                users.add(UserDto.builder()
                        .id(rs.getLong(1))
                        .login(rs.getString(2))
                        .hash_password(rs.getString(3))
                        .email(rs.getString(4))
                        .full_name(rs.getString(5))
                        .nickname(rs.getString(6))
                        .register_date(rs.getTimestamp(7))
                        .build());
            }
            return users;
        } catch (SQLException sqlException) {
            throw new NoSuchElementException("Проблема с обращением к базе данных");
        }
    }

    @Override
    public void updateUserById(UserDto userDto) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET login = ?, hash_password = ?, " +
                     "email = ?, full_name = ?, nickname = ?, registration_date= ? WHERE id = ?");
        ) {
            statement.setString(1, userDto.getLogin());
            statement.setString(2, userDto.getHash_password());
            statement.setString(3, userDto.getEmail());
            statement.setString(4, userDto.getFull_name());
            statement.setString(5, userDto.getNickname());
            statement.setTimestamp(6, userDto.getRegister_date());
            statement.setLong(7, userDto.getId());
            log.debug("Update");
            int count = statement.executeUpdate();
            System.out.println(count);
            if (count == 1) {
                log.info("Update Complete!");
                System.out.println(statement);
            } else {
                log.warn("Warning!!");
            }
        } catch (SQLException sqlException) {
            sqlException.getStackTrace();
            log.error(sqlException.getMessage());
            throw new NoSuchElementException("Проблема с обращением к базе данных");
        }
    }

    @Override
    public int deleteUserById(Long id) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
        ) {
            statement.setLong(1, id);
            log.debug("Delete");
            int count = statement.executeUpdate();
            System.out.println(count);
            if (count == 1) {
                log.info("Delete Complete!");
            } else {
                log.warn("Warning!! Ни одна строка не была удалена!");
            }
            return count;
        } catch (SQLException sqlException) {
            sqlException.getStackTrace();
            log.error(sqlException.getMessage());
            throw new NoSuchElementException("Проблема с обращением к базе данных");
        }
    }

    @Override
    public int insertUser(UserDto userDto) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login, hash_password, email, full_name, nickname) VALUES (?, ?, ?,?,?);")
        ) {
            statement.setString(1, userDto.getLogin());
            statement.setString(2, userDto.getHash_password());
            statement.setString(3, userDto.getEmail());
            statement.setString(4, userDto.getFull_name());
            statement.setString(5, userDto.getNickname());
            log.debug("Insert");
            int count = statement.executeUpdate();
            System.out.println(count);
            if (count == 1) {
                log.info("Insert Complete!");
                System.out.println(statement);
            } else {
                log.warn("Warning!!");
            }
            return count;
        } catch (SQLException sqlException) {
            sqlException.getStackTrace();
            log.error(sqlException.getMessage());
            throw new NoSuchElementException("Проблема с обращением к базе данных");
        }
    }

}
