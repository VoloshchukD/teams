package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.Task;
import by.voloshchuk.entity.User;
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
 * Async command for adding new task to project.
 *
 * @author Daniil Voloshchuk
 */
public class AddTaskCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Task task = createTask(request);
        TaskService taskService = serviceProvider.getTaskService();
        try {
            taskService.addTask(task);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

    private Task createTask(HttpServletRequest request) {
        Task task = new Task();
        Long projectId = Long.parseLong(request.getParameter(AsyncCommandParameter.PROJECT_ID));
        User user = new User();
        user.setId(Long.parseLong(request.getParameter(AsyncCommandParameter.USER_ID)));
        task.setDeveloper(user);
        Project project = new Project();
        project.setId(projectId);
        task.setProject(project);
        task.setStatus(Task.TaskStatus.TO_DO);
        task.setPlannedTime(Integer.parseInt(request.getParameter(AsyncCommandParameter.TASK_HOURS)));
        task.setName(request.getParameter(AsyncCommandParameter.TASK_NAME));
        task.setDetails(request.getParameter(AsyncCommandParameter.TASK_DETAILS));
        return task;
    }

}
