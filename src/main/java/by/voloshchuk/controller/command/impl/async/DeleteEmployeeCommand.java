package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.RequestParameter;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Async command for deleting user from project.
 *
 * @author Daniil Voloshchuk
 */
public class DeleteEmployeeCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Long userId = Long.parseLong(request.getParameter(RequestParameter.USER_ID));
        Long projectId = Long.parseLong(request.getParameter(RequestParameter.PROJECT_ID));

        UserService userService = serviceProvider.getUserService();
        try {
            userService.removeUserFromProject(userId, projectId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

}
