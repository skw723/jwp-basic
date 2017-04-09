package next.controller;

import next.dao.UserDao;
import next.model.User;
import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new UserDao().findByUserId(request.getParameter("userId"));

        if (user == null) {
            return "/user/login_failed.jsp";
        }

        if (user.getPassword().equals(request.getParameter("password"))) {
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        } else {
            return "/user/login_failed.jsp";
        }
    }
}
