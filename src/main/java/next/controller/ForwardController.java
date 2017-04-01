package next.controller;


import next.web.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
    private String viewName;

    public ForwardController(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return viewName;
    }
}
