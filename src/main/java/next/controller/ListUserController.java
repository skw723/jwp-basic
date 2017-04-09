package next.controller;

import next.dao.UserDao;
import next.web.Controller;
import next.web.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (UserSessionUtils.isLogined(request.getSession()) == false) {
            return "redirect:/users/loginForm";
        }
        request.setAttribute("users", new UserDao().findAll());
        return "/user/list.jsp";
    }
}
