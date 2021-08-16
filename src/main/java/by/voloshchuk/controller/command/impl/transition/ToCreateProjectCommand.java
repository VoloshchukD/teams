package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TechnicalTaskService;
import by.voloshchuk.util.RegexProperty;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to move to page with project creation form.
 *
 * @author Daniil Voloshchuk
 */
public class ToCreateProjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long technicalTaskId = Long.parseLong(
                request.getParameter(RequestParameter.TECHNICAL_TASK_ID));
        Long customerId = Long.parseLong(
                request.getParameter(RequestParameter.CUSTOMER_ID));

        TechnicalTask technicalTask = null;
        TechnicalTaskService technicalTaskService =
                serviceProvider.getTechnicalTaskService();
        try {
            technicalTask = technicalTaskService.findTechnicalTaskById(technicalTaskId);
            if (technicalTask != null) {
                technicalTask.setCustomerId(customerId);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        request.setAttribute(RequestParameter.TECHNICAL_TASK, technicalTask);
        request.setAttribute(CommandAttribute.PROJECT_NAME_REGEX,
                RegexProperty.PROPERTY_PROJECT_NAME_REGEX);
        request.setAttribute(CommandAttribute.PROJECT_DESCRIPTION_REGEX,
                RegexProperty.PROPERTY_PROJECT_DESCRIPTION_REGEX);

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.PROJECT_CREATION_JSP);
        return router;
    }

}
