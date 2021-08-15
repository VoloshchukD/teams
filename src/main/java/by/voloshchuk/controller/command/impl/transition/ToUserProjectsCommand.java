package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.controller.command.*;
import by.voloshchuk.util.RegexProperty;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command to move to users project.
 *
 * @author Daniil Voloshchuk
 */
public class ToUserProjectsCommand implements Command {

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

        request.setAttribute(CommandAttribute.PROJECT_NAME_REGEX,
                RegexProperty.PROPERTY_PROJECT_NAME_REGEX);
        request.setAttribute(CommandAttribute.PROJECT_DESCRIPTION_REGEX,
                RegexProperty.PROPERTY_PROJECT_DESCRIPTION_REGEX);

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.PROJECTS_JSP);
        return router;
    }

}
