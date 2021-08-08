package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.CommandRouter;
import by.voloshchuk.servlet.command.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProjectCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        ProjectService projectService = serviceProvider.getProjectService();
        Long projectId = Long.parseLong(request.getParameter(RequestParameter.PROJECT_ID));
        try {
            projectService.removeProject(projectId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.TO_PROJECTS);
        return router;
    }

}
