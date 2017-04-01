package next.controller;

import next.web.Controller;
import next.web.UserSessionUtils;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (UserSessionUtils.isLogined(request.getSession())) {
            return "/user/profile.jsp";
        }
        return "redirect:/users/loginForm";
    }
}
