package next.web;

import next.model.User;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

public class UserSessionUtils {
    public static boolean isLogined(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return false;
        }
        return true;
    }
}
