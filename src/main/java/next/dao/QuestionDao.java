package next.dao;

import core.db.JdbcTemplate;
import core.db.RowMapper;
import next.model.Question;

import java.util.List;

public class QuestionDao {
    public List<Question> findAll() {
        RowMapper<Question> mapper = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
        return new JdbcTemplate().query("SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS", mapper);
    }

    public Question findByQuestionId(String questionId) {
        RowMapper<Question> mapper = rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"), rs.getString("contents"), rs.getDate("createdDate"), rs.getInt("countOfAnswer"));
        return new JdbcTemplate().queryForObject("SELECT questionId, writer, title, contents, createdDate, countOfAnswer FROM QUESTIONS WHERE questionId=?", mapper, questionId);
    }

    public void updateAnswerCountIncrease(long questionId) {
        new JdbcTemplate().update("UPDATE QUESTIONS SET countOfAnswer=countOfAnswer+1 WHERE questionId=?", questionId);
    }

    public void updateAnswerCountDecrease(long questionId) {
        new JdbcTemplate().update("UPDATE QUESTIONS SET countOfAnswer=countOfAnswer-1 WHERE questionId=?", questionId);
    }
}