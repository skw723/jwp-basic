package next.controller.qna;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Result;
import next.web.Controller;
import next.web.JsonView;
import next.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class DeleteAnswerController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> result;
        try {
            AnswerDao answerDao = new AnswerDao();
            answerDao.delete(request.getParameter("answerId"));
            QuestionDao questionDao = new QuestionDao();
            questionDao.updateAnswerCountDecrease(Long.parseLong(request.getParameter("questionId")));
        } catch (Exception e) {
            result = Result.fail(e.getMessage());
        }
        result = Result.ok();
        return new ModelAndView(new JsonView()).addObject("result", result);
    }
}
