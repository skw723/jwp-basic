package next.controller.user;

import next.web.Controller;
import next.web.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (UserSessionUtils.isLogined(request.getSession())) {
            return "/user/update.jsp";
        }
        return "redirect:/users/loginForm";
    }
}
