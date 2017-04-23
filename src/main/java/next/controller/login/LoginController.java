package next.controller.login;

import next.dao.UserDao;
import next.model.User;
import next.web.Controller;
import next.web.JsonView;
import next.web.JspView;
import next.web.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new UserDao().findByUserId(request.getParameter("userId"));
        ModelAndView mav = new ModelAndView();

        if (user == null) {
            return mav.setView(new JspView("/user/login_failed.jsp"));
        }

        if (user.getPassword().equals(request.getParameter("password"))) {
            request.getSession().setAttribute("user", user);
            return mav.setView(new JspView("redirect:/home"));
        } else {
            return mav.setView(new JspView("/user/login_failed.jsp"));
        }
    }
}
