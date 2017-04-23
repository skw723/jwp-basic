package next.controller.login;

import next.web.Controller;
import next.web.JspView;
import next.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return new ModelAndView(new JspView("redirect:/home"));
    }
}
