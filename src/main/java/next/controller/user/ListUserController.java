package next.controller.user;

import next.dao.UserDao;
import next.web.Controller;
import next.web.JspView;
import next.web.ModelAndView;
import next.web.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView();
        if (UserSessionUtils.isLogined(request.getSession()) == false) {
            return mav.setView(new JspView("redirect:/users/loginForm"));
        }
        return mav.setView(new JspView("/user/list.jsp")).addObject("users", new UserDao().findAll());
    }
}
