package next.dao;

import core.db.JdbcTemplate;
import core.db.RowMapper;
import next.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public void insert(User user) throws SQLException {
        new JdbcTemplate().update("INSERT INTO USERS VALUES (?, ?, ?, ?)", user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public User findByUserId(String userId) throws SQLException {
        RowMapper<User> mapper = rs -> rs.next() ? new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email")) : null;
        return new JdbcTemplate().queryForObject("SELECT userId, password, name, email FROM USERS WHERE userid=?", mapper, userId);
    }

    public List<User> findAll() throws SQLException {
        RowMapper<User> mapper = rs -> new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
        return new JdbcTemplate().query("SELECT userId, password, name, email FROM USERS", mapper);
    }

    public void update(User user) throws SQLException {
        new JdbcTemplate().update("UPDATE USERS SET password=?, name=?, email=? WHERE userId=?", user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }
}
