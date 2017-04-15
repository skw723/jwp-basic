package next.dao;

import core.db.JdbcTemplate;
import core.db.RowMapper;
import next.model.Answer;
import sun.management.jdp.JdpController;

import java.util.List;

public class AnswerDao {
    public List<Answer> findByQuestionId(String questionId) {
        RowMapper<Answer> mapper = rs -> new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"), rs.getDate("createdDate"), rs.getLong("questionId"));
        return new JdbcTemplate().query("SELECT answerId, writer, contents, createdDate, questionId FROM ANSWERS WHERE questionId=?", mapper, questionId);
    }

    public void insert(Answer answer) {
        new JdbcTemplate().update("INSERT INTO ANSWERS(writer, contents, createdDate, questionId) VALUES(?,?,?,?)", answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
    }

    public void delete(String answerId) {
        new JdbcTemplate().update("DELETE FROM ANSWERS WHERE answerId=?", answerId);
    }
}
