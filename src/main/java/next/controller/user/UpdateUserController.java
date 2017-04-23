package next.controller.user;

import next.dao.UserDao;
import next.model.User;
import next.web.Controller;
import next.web.JspView;
import next.web.ModelAndView;
import next.web.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (UserSessionUtils.isLogined(request.getSession())) {
            UserDao userDao = new UserDao();
            String userId = request.getParameter("userId");
            User user = userDao.findByUserId(userId);
            if (user == null) {
                throw new IllegalStateException("user info is null");
            }
            user.update(new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"), request.getParameter("email")));
            userDao.update(user);
        }

        return new ModelAndView(new JspView("redirect:/home"));
    }
}
