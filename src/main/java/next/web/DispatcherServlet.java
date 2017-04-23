package next.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMapping mappings = new RequestMapping();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI();
        Controller controller = mappings.getController(path);

        if (controller != null) {
            try {
                ModelAndView mav = controller.execute(req, resp);
                mav.getView().render(mav.getModel(), req, resp);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new ServletException(e);
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
