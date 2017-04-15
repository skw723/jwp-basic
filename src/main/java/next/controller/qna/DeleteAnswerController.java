package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Result;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteAnswerController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Result result = new Result();
        try {
            AnswerDao answerDao = new AnswerDao();
            answerDao.delete(request.getParameter("answerId"));
            QuestionDao questionDao = new QuestionDao();
            questionDao.updateAnswerCountDecrease(Long.parseLong(request.getParameter("questionId")));
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().print(mapper.writeValueAsString(result));
        return null;
    }
}
