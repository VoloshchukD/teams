package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TaskService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Async command for tracking task hours.
 *
 * @author Daniil Voloshchuk
 */
public class UpdateTaskHoursCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Long taskId = Long.parseLong(
                request.getParameter(AsyncCommandParameter.TASK_ID));
        Integer hours = Integer.parseInt(
                request.getParameter(AsyncCommandParameter.TASK_HOURS));
        TaskService taskService = serviceProvider.getTaskService();
        try {
            taskService.updateTaskHours(taskId, hours);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

}
