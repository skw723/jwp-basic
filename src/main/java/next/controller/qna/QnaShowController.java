package next.controller.qna;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaShowController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();
        String questionId = request.getParameter("questionId");
        request.setAttribute("qna", questionDao.findByQuestionId(questionId));
        request.setAttribute("answer", answerDao.findByQuestionId(questionId));
        return "/qna/show.jsp";
    }
}
