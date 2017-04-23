package next.controller.qna;

import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.web.Controller;
import next.web.JspView;
import next.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaShowController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();
        String questionId = request.getParameter("questionId");
        return new ModelAndView(new JspView("/qna/show.jsp")).addObject("qna", questionDao.findByQuestionId(questionId)).addObject("answer", answerDao.findByQuestionId(questionId));
    }
}
