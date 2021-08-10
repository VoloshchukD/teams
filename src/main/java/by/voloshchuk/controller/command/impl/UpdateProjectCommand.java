package by.voloshchuk.controller.command.impl;

import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
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

public class UpdateProjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ProjectService projectService = serviceProvider.getProjectService();
        Project project = createProject(request);
        try {
            projectService.updateProject(project);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.TO_PROJECTS);
        return router;
    }

    private Project createProject(HttpServletRequest request) {
        Project project = new Project();
        Long projectId = Long.parseLong(request.getParameter(RequestParameter.PROJECT_ID));
        project.setId(projectId);
        project.setName(request.getParameter(RequestParameter.PROJECT_NAME));
        project.setDescription(request.getParameter(RequestParameter.PROJECT_DESCRIPTION));
        return project;
    }

}
