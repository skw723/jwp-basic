package next.controller.user;

import next.web.Controller;
import next.web.JspView;
import next.web.ModelAndView;
import next.web.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        if (UserSessionUtils.isLogined(request.getSession())) {
            return mav.setView(new JspView("/user/update.jsp"));
        }
        return mav.setView(new JspView("redirect:/users/loginForm"));
    }
}
