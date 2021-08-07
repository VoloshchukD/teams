package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.ProjectDto;
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
        ProjectDto projectDto = createProjectDto(request);
        ProjectService projectService = serviceProvider.getProjectService();
        boolean created = false;
        try {
            created = projectService.addProject(projectDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router;
        if (created) {
            request.getSession().setAttribute(CommandAttribute.RECENTLY_CREATED_PROJECT , projectDto.getProject());
            router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_PROJECTS);
        } else {
            request.setAttribute(CommandAttribute.ERROR, true);
            router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.TO_CREATE_PROJECT);
        }
        return router;
    }

    private ProjectDto createProjectDto(HttpServletRequest request) {
        ProjectDto projectDto = new ProjectDto();
        Project project = new Project();
        project.setName(request.getParameter(RequestParameter.PROJECT_NAME));
        project.setDescription(request.getParameter(RequestParameter.PROJECT_DESCRIPTION));
        project.setState(Project.ProjectStatus.STARTING);
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setId(Long.parseLong(request.getParameter(RequestParameter.TECHNICAL_TASK_ID)));
        project.setTechnicalTask(technicalTask);
        projectDto.setProject(project);
        projectDto.setCustomerId(Long.parseLong(request.getParameter(RequestParameter.CUSTOMER_ID)));
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        projectDto.setManagerId(userId);
        return projectDto;
    }

}
