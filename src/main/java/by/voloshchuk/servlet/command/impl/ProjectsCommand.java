package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.servlet.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProjectsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String state = request.getParameter(RequestParameter.PROJECT_STATE);
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        List<Project> projects = null;
        ProjectService projectService = serviceProvider.getProjectService();
        try {
            projects = projectService.findProjectsByUserIdAndState(userId, state);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        request.setAttribute(RequestParameter.PROJECTS, projects);
        request.setAttribute(RequestParameter.PROJECT_STATE, state);

        Project recentlyCreatedProject = (Project) request.getSession()
                .getAttribute(CommandAttribute.RECENTLY_CREATED_PROJECT);
        if (recentlyCreatedProject != null) {
            request.setAttribute(CommandAttribute.RECENTLY_CREATED_PROJECT_NAME,
                    recentlyCreatedProject.getName());
            request.getSession()
                    .removeAttribute(CommandAttribute.RECENTLY_CREATED_PROJECT);
        }

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.PROJECTS_JSP);
        return router;
    }

}
