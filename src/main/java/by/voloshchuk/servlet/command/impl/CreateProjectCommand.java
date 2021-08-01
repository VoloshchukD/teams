package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
import by.voloshchuk.servlet.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateProjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Project project = createProject(request);
        ProjectService projectService = serviceProvider.getProjectService();
        try {
            projectService.addProject(project);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.SEEK_EMPLOYEES + "&" + RequestParameter.TECHNICAL_TASK_ID
                        + "=" + project.getTechnicalTask().getId() +
                "&" + RequestParameter.PROJECT_ID
                        + "=" + project.getId());
        return router;
    }

    private Project createProject(HttpServletRequest request) {
        Project project = new Project();
        project.setName(request.getParameter(RequestParameter.PROJECT_NAME));
        project.setDescription(request.getParameter(RequestParameter.PROJECT_DESCRIPTION));
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setId(Long.parseLong(request.getParameter(RequestParameter.TECHNICAL_TASK_ID)));
        project.setTechnicalTask(technicalTask);
        return project;
    }

}
