package dao;

import dto.PageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PageDAOImpl implements PageDAO {
    private static Logger log = LoggerFactory.getLogger(PageDAOImpl.class);

    @Override
    public PageDto getPageById(Long id) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM page WHERE id = ?");
        ) {
            statement.setLong(1, id);
            log.debug("getPageById {}", id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new PageDto(rs.getLong(1), rs.getObject(2, UUID.class), rs.getLong(3), rs.getString(4), rs.getString(5));
            } else {
                log.warn("No page found for id = {}", id);
            }
        } catch (SQLException sqlException) {
            throw new NoSuchElementException("Проблема с обращением к базе данных");
        }
        return null;
    }

    @Override
    public void updatePageById(PageDto pageDto) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE pages SET (name = ?, context = ? WHERE id = ?");
        ) {
            statement.setString(1, pageDto.getName());
            statement.setString(2, pageDto.getContext());
            statement.setDouble(3, pageDto.getId());
            log.debug("Update");
            int rs = statement.executeUpdate();
            System.out.println(rs);
            if (rs == 1) {
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
    public void deletePageById(Long id) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE TABLE page WHERE id = ?");
        ) {
            statement.setLong(1, id);
            log.debug("Delete");
            ResultSet rs = statement.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                rs.deleteRow();
                log.info("Delete Complete!");
            } else {
                log.warn("Warning!!");
            }
        } catch (SQLException sqlException) {
            sqlException.getStackTrace();
            log.error(sqlException.getMessage());
            throw new NoSuchElementException("Проблема с обращением к базе данных");
        }
    }
}
