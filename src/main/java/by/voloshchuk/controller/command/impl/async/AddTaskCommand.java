package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.Task;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.TaskDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TaskService;
import by.voloshchuk.util.DtoBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Async command for adding new task to project.
 *
 * @author Daniil Voloshchuk
 */
public class AddTaskCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TaskDto taskDto = DtoBuilder.buildTaskDto(request);
        taskDto.setProjectId(request.getParameter(AsyncCommandParameter.PROJECT_ID));
        TaskService taskService = serviceProvider.getTaskService();
        try {
            taskService.addTask(taskDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

}
