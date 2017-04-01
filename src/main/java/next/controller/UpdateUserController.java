package next.controller;

import core.db.DataBase;
import next.model.User;
import next.web.Controller;
import next.web.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (UserSessionUtils.isLogined(request.getSession())) {
            String userId = request.getParameter("userId");
            User user = DataBase.findUserById(userId);
            if (user == null) {
                throw new IllegalStateException("user info is null");
            }
            User updateUser = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"), request.getParameter("email"));
            DataBase.addUser(updateUser);
        }

        return "redirect:/home";
    }
}
