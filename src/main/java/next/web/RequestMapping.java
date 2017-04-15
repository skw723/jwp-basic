package next.web;

import next.controller.*;
import next.controller.login.LoginController;
import next.controller.login.LogoutController;
import next.controller.qna.AddAnswerController;
import next.controller.qna.QnaShowController;
import next.controller.user.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private final Map<String, Controller> mappings = new HashMap<>();

    public RequestMapping() {
        mappings.put("/", new HomeController());
        mappings.put("/home", new HomeController());
        mappings.put("/users/form", new ForwardController("/user/form.jsp"));
        mappings.put("/users/create", new CreateUserController());
        mappings.put("/users/loginForm", new ForwardController("/user/login.jsp"));
        mappings.put("/users/login", new LoginController());
        mappings.put("/users", new ListUserController());
        mappings.put("/users/profile", new ProfileController());
        mappings.put("/users/logout", new LogoutController());
        mappings.put("/users/updateForm", new UpdateFormUserController());
        mappings.put("/users/update", new UpdateUserController());
        mappings.put("/qna/show", new QnaShowController());
        mappings.put("/api/qna/addAnswer", new AddAnswerController());
    }

    public Controller getController(String path) {
        return mappings.get(path);
    }
}
