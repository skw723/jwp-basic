package core.db;

import core.jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    public <T> List<T> query(String sql, PreparedStatementSetter setter, RowMapper<T> mapper) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            setter.setValues(pstmt);
            rs = pstmt.executeQuery();
            List<T> resultLIst = new ArrayList<T>();
            while (rs.next()) {
                resultLIst.add(mapper.mapRow(rs));
            }
            return resultLIst;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
    }

    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... parameters) {
        return query(sql, createPreparedStatementSetter(parameters), mapper);
    }

    public <T> T queryForObject(String sql, PreparedStatementSetter setter, RowMapper<T> mapper) {
        List<T> result = query(sql, setter, mapper);
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

    public <T> T queryForObject(String sql, RowMapper<T> mapper, Object... parameters) {
        return queryForObject(sql, createPreparedStatementSetter(parameters), mapper);
    }

    public void update(String sql, PreparedStatementSetter setter) {
        try (Connection con = ConnectionManager.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            setter.setValues(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public void update(String sql, Object... parameters) {
        update(sql, createPreparedStatementSetter(parameters));
    }

    private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pstmt) throws SQLException {
                if (parameters == null) {
                    return;
                }
                for (int i = 0; i < parameters.length; i++) {
                    pstmt.setObject(i + 1, parameters[i]);
                }
            }
        };
    }

}
