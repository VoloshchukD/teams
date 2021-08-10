package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TaskService;
import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateTaskStatusCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long taskId = Long.parseLong(request.getParameter(AsyncCommandParameter.TASK_ID));
        String status = request.getParameter(AsyncCommandParameter.TASK_STATUS);
        TaskService taskService = serviceProvider.getTaskService();
        try {
            taskService.updateTaskStatus(taskId, status);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

}
