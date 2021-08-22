package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TaskService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Async command to delete task from project.
 *
 * @author Daniil Voloshchuk
 */
public class DeleteTaskCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        TaskService taskService = serviceProvider.getTaskService();
        Long taskId = Long.parseLong(request.getParameter(RequestParameter.TASK_ID));
        try {
            taskService.removeTask(taskId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

}
