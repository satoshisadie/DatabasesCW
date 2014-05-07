package dao;

import beans.Post;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcPostDao implements PostDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Post post) {

    }

    @Override
    public Post get(int postId) {
        return null;
    }

    @Override
    public void update(Post post) {

    }

    @Override
    public void delete(int postId) {

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
