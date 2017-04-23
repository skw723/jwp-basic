package next.web;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View {
    public static final String REDIRECT_PREFIX = "redirect:";
    private String viewName;

    public JspView() {
    }

    public JspView(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (Strings.isNullOrEmpty(viewName)) {
            throw new IllegalStateException("do not set viewName");
        }

        for (String key : model.keySet()) {
            request.setAttribute(key, model.get(key));
        }

        if (viewName.startsWith(REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(9, viewName.length()));
        } else {
            request.getRequestDispatcher(viewName).forward(request, response);
        }
    }
}
