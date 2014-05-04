package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class TestDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public String GetParts() {
        String query = "SELECT p.Color " +
                       "FROM dbo.Parts p " +
                       "WHERE p.Id = 'P1'";

        return jdbcTemplate.queryForObject(query, String.class);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
