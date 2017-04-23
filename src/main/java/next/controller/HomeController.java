package next.controller;

import next.dao.QuestionDao;
import next.web.Controller;
import next.web.JspView;
import next.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao dao = new QuestionDao();
        return new ModelAndView(new JspView("home.jsp")).addObject("qna", dao.findAll());
    }
}
