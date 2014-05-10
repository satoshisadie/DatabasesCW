package dao;

import beans.*;
import com.google.common.base.Joiner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createAdministrator(User user) {
        String query = "INSERT INTO [User](Login, Password, FirstName, LastName, Email, RoleId) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), 1);
    }

    @Override
    public void createUser(User user) {
        String query = "INSERT INTO [User](Login, Password, FirstName, LastName, Email, RoleId) " +
                       "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(query, user.getLogin(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), 4);
    }

    @Override
    public User get(int userId) {
        String query = "SELECT u.*, r.Name as Role " +
                       "FROM [User] u " +
                       "JOIN Role r ON r.Id = u.RoleId " +
                       "WHERE u.Id = ?";

        List<User> result = jdbcTemplate.query(query, new UserRowMapper(), userId);
        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public User get(String login, String password) {
        String query = "SELECT u.*, r.Name as Role " +
                       "FROM [User] u " +
                       "JOIN Role r ON r.Id = u.RoleId " +
                       "WHERE u.Login = ? AND u.Password = ?";

        List<User> result = jdbcTemplate.query(query, new UserRowMapper(), login, password);
        return result.size() != 0 ? result.get(0) : null;
    }

    @Override
    public List<User> getAll(Iterable<Integer> usersIds) {
        String query = "SELECT u.*, r.Name as Role " +
                       "FROM [User] u " +
                       "JOIN Role r ON r.Id = u.RoleId " +
                       "WHERE u.Id IN (" + Joiner.on(",").join(usersIds) +")";

        return jdbcTemplate.query(query, new UserRowMapper());
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

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirstName(rs.getString("firstName"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("role"));
            user.setDateJoined(rs.getTimestamp("dateJoined"));
            return user;
        }
    }
}
