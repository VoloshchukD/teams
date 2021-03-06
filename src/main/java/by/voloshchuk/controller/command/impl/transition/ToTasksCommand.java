package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.entity.Task;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TaskService;
import by.voloshchuk.util.RegexProperty;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command to move to view all tasks on the project.
 *
 * @author Daniil Voloshchuk
 */
public class ToTasksCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long projectId = Long.parseLong(request.getParameter(RequestParameter.PROJECT_ID));
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        List<Task> tasks = null;
        TaskService taskService = serviceProvider.getTaskService();
        try {
            tasks = taskService.findTasksByProjectIdAndUserId(projectId, userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        request.setAttribute(RequestParameter.TASKS, tasks);
        request.setAttribute(CommandAttribute.TASK_NAME_REGEX,
                RegexProperty.PROPERTY_TASK_NAME_REGEX);
        request.setAttribute(CommandAttribute.TASK_DETAILS_REGEX,
                RegexProperty.PROPERTY_TASK_DETAILS_REGEX);
        request.setAttribute(CommandAttribute.TASK_HOURS_REGEX,
                RegexProperty.PROPERTY_TASK_HOURS_REGEX);

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.TASKS_JSP);
        return router;
    }

}
