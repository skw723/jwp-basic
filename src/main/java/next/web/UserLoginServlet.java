package next.web;

import core.db.DataBase;
import next.dao.UserDao;
import next.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = DataBase.findUserById(req.getParameter("userId"));
        if (user != null) {
            if (user.getPassword().equals(req.getParameter("password"))) {
                req.getSession().setAttribute("user", user);
                resp.sendRedirect("/index.html");
            } else {
                resp.sendRedirect("/user/login_failed.html");
            }
        } else {
            resp.sendRedirect("/user/login_failed.html");
        }
    }
}
