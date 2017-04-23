package next.controller.qna;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.web.Controller;
import next.web.JsonView;
import next.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class AddAnswerController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Answer answer = new Answer(request.getParameter("writer"), request.getParameter("contents"), new Date(), Long.parseLong(request.getParameter("questionId")));
        AnswerDao answerDao = new AnswerDao();
        answerDao.insert(answer);
        QuestionDao questionDao = new QuestionDao();
        questionDao.updateAnswerCountIncrease(answer.getQuestionId());
        return new ModelAndView(new JsonView()).addObject("answer", answer);
    }
}
