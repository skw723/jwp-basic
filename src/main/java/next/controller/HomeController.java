package next.controller;

import next.dao.QuestionDao;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao dao = new QuestionDao();
        request.setAttribute("qna", dao.findAll());
        return "home.jsp";
    }
}
