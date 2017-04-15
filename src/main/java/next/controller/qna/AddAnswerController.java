package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

public class AddAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(request.getParameter("writer"), request.getParameter("contents"), new Date(), Long.parseLong(request.getParameter("questionId")));
        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(answer);
        QuestionDao questionDao = new QuestionDao();
        questionDao.updateAnswerCount(answer.getQuestionId());

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(mapper.writeValueAsString(answer));
        return null;
    }
}
