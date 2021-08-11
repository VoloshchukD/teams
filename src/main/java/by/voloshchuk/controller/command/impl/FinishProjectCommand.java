package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to finish the project.
 *
 * @author Daniil Voloshchuk
 */
public class FinishProjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String state = Project.ProjectStatus.FINISHED.toString();
        Long projectId = Long.parseLong(request.getParameter(RequestParameter.PROJECT_ID));
        ProjectService projectService = serviceProvider.getProjectService();
        try {
            projectService.updateProjectStatus(projectId, state);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_PROJECTS);
        return router;
    }

}