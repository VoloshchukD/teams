package by.voloshchuk.controller.command.impl;

import by.voloshchuk.entity.Task;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TaskService;
import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.controller.command.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to update task data.
 *
 * @author Daniil Voloshchuk
 */
public class UpdateTaskCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        TaskService taskService = serviceProvider.getTaskService();
        Task task = createTask(request);
        try {
            taskService.updateTask(task);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.TASKS + request.getParameter(RequestParameter.PROJECT_ID));
        return router;
    }

    private Task createTask(HttpServletRequest request) {
        Task task = new Task();
        Long taskId = Long.parseLong(request.getParameter(RequestParameter.TASK_ID));
        task.setId(taskId);
        task.setName(request.getParameter(RequestParameter.TASK_NAME));
        task.setDetails(request.getParameter(RequestParameter.TASK_DETAILS));
        task.setPlannedTime(Integer.parseInt(request.getParameter(RequestParameter.TASK_HOURS)));
        User user = new User();
        user.setId(Long.parseLong(request.getParameter(RequestParameter.USER_ID)));
        task.setDeveloper(user);
        return task;
    }

}
