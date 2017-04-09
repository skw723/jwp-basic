package next.controller;

import next.dao.UserDao;
import next.model.User;
import next.web.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"), request.getParameter("email"));
        LOGGER.debug("user : {}", user);
        UserDao userDao = new UserDao();
        userDao.insert(user);
        return "redirect:/home ";
    }
}
