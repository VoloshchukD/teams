package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.entity.dto.ProjectDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.util.RequestParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to create project.
 *
 * @author Daniil Voloshchuk
 */
public class CreateProjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        ProjectDto projectDto = RequestParser.buildProjectDto(request);
        ProjectService projectService = serviceProvider.getProjectService();
        boolean created = false;
        try {
            created = projectService.addProject(projectDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router;
        if (created) {
            request.getSession().setAttribute(CommandAttribute.RECENTLY_CREATED_PROJECT,
                    projectDto.getProject());
            router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                    CommandPath.TO_PROJECTS);
        } else {
            request.setAttribute(CommandAttribute.ERROR, true);
            router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                    CommandPath.TO_CREATE_PROJECT);
        }
        return router;
    }

}
