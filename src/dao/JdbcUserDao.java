package dao;

import beans.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(User user) {
        String query = "INSERT INTO [User](Login, Password, FirstName, LastName, Email, DateJoined) " +
                       "VALUES (?, ?, ?, ?, ?, (SELECT GETDATE()))";

        jdbcTemplate.update(query, user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    @Override
    public User get(int userId) {
        String query = "SELECT * " +
                       "FROM [User] u " +
                       "WHERE u.Id = ?";

        List<User> result = jdbcTemplate.query(query, (resultSet, rowNum) -> {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setEmail(resultSet.getString("email"));
            user.setDateJoined(resultSet.getDate("dateJoined"));
            return user;
        }, userId);

        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public User get(String login, String password) {
        String query = "SELECT * " +
                       "FROM [User] u " +
                       "WHERE u.Login = ? AND u.Password = ?";

        List<User> result = jdbcTemplate.query(query, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setDateJoined(rs.getDate("dateJoined"));
            return user;
        }, login, password);

        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int userId) {

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
