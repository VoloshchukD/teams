package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.util.RegexProperty;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command to move to page with bill creation form.
 *
 * @author Daniil Voloshchuk
 */
public class ToBillCreationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        List<Project> projects = null;
        ProjectService projectService = serviceProvider.getProjectService();
        try {
            projects = projectService.findProjectsByUserIdAndState(userId,
                    Project.ProjectStatus.IN_PROGRESS.toString());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        request.setAttribute(RequestParameter.PROJECTS, projects);
        request.setAttribute(CommandAttribute.BILL_AMOUNT_REGEX,
                RegexProperty.PROPERTY_BILL_AMOUNT_REGEX);
        request.setAttribute(CommandAttribute.BILL_INFORMATION_REGEX,
                RegexProperty.PROPERTY_BILL_INFORMATION_REGEX);

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.BILL_CREATION_JSP);
        return router;
    }

}
