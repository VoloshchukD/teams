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
import java.util.List;

/**
 * Command to move to page with users technical tasks.
 *
 * @author Daniil Voloshchuk
 */
public class ToUserTechnicalTasksCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskService technicalTaskService = serviceProvider.getTechnicalTaskService();
        try {
            technicalTasks = technicalTaskService.findTechnicalTasksByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        request.setAttribute(RequestParameter.TECHNICAL_TASKS, technicalTasks);
        request.setAttribute(CommandAttribute.TECHNICAL_TASK_NAME_REGEX,
                RegexProperty.PROPERTY_TECHNICAL_TASK_NAME_REGEX);
        request.setAttribute(CommandAttribute.TECHNICAL_TASK_OVERVIEW_REGEX,
                RegexProperty.PROPERTY_TECHNICAL_TASK_OVERVIEW_REGEX);
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.TECHNICAL_TASKS_JSP);
        return router;
    }

}
