package mapper;

import entity.Page;
import lombok.Builder;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Builder
public class PageMapper implements RowMapper<Page> {
    @Override
    public Page mapRow(ResultSet resultSet, int i) throws SQLException {
        return Page.builder()

                .build();
    }
}
