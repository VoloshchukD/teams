package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.*;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TaskService;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.servlet.command.AsyncCommand;
import by.voloshchuk.servlet.command.CommandAttribute;
import by.voloshchuk.servlet.command.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        Long projectId = Long.parseLong(request.getParameter(RequestParameter.PROJECT_ID));
        User user = new User();
        user.setId(Long.parseLong(request.getParameter(RequestParameter.USER_ID)));
        task.setDeveloper(user);
        Project project = new Project();
        project.setId(projectId);
        task.setProject(project);
        task.setStatus("ToDo");
        task.setHours(Integer.parseInt(request.getParameter("hours")));
        task.setName(request.getParameter(RequestParameter.TASK_NAME));
        task.setDetails(request.getParameter(RequestParameter.TASK_DETAILS));
        return task;
    }

}
