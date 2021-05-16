package dao;

import dto.PageDto;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Slf4j
public class PageDAOImpl implements PageDAO {

    @Override
    public PageDto getPageById(Long id) {
        //try(...){} - try-with-resources конструкции, чтобы не закрывать за собой соединение и statement
        try (Connection connection = new DBConnection().getConnection();//создаю подключение к БД
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM pages WHERE id = ?")//делаю предподготовленный(против SQL инъекции) запрос
        ) {
            //передаю значения вместо ?. Суть в том, что для каждого указываю конкретный тип:
            // setLong/setString..то есть ничего другого в запрос не засунешь. В скобках порядковый номер "?" и значение
            statement.setLong(1, id);
            log.debug("getPageById {}", id);
            //получаю результат выполнения запроса(для разных запросов метод может отличаться)
            ResultSet rs = statement.executeQuery();
            //перебираем результаты
            if (rs.next()) {
                //если строка не пустая, формируем DTOшку
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
    public List<PageDto> getAllPages() {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM pages");
        ) {
            log.debug("get all pages");
            ResultSet rs = statement.executeQuery();
            List<PageDto> pages = new ArrayList<>();
            while (rs.next()) {
                pages.add(PageDto.builder()
                        .id(rs.getLong(1))
                        .catalog_id(rs.getLong(3))
                        .name(rs.getString(4))
                        .content(rs.getString(5))
                        .build());
            }
            return pages;
        } catch (SQLException sqlException) {
            throw new NoSuchElementException("Проблема с обращением к базе данных");
        }
    }

    @Override
    public void updatePageById(PageDto pageDto) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE pages SET external_id = DEFAULT, catalog_id = ?, name = ?, content = ? WHERE id = ?");
        ) {
            statement.setLong(1, pageDto.getCatalog_id());
            statement.setString(2, pageDto.getName());
            statement.setString(3, pageDto.getContent());
            statement.setDouble(4, pageDto.getId());
            log.debug("Update");
            int count = statement.executeUpdate();//получаем количество обновленных строк
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
    public int deletePageById(Long id) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM pages WHERE id = ?");
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
    public int insertPage(PageDto pageDto) {
        try (Connection connection = new DBConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO pages (catalog_id, name, content) VALUES (?, ?, ?);")
        ) {
            statement.setLong(1, pageDto.getCatalog_id());
            statement.setString(2, pageDto.getName());
            statement.setString(3, pageDto.getContent());
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
