package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TechnicalTaskService;
import by.voloshchuk.util.RegexProperty;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command to move to requirements managing page.
 *
 * @author Daniil Voloshchuk
 */
public class ToCreateRequirementCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskService technicalTaskService = serviceProvider.getTechnicalTaskService();
        try {
            technicalTasks = technicalTaskService.findTechnicalTasksByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        request.setAttribute(CommandAttribute.REQUIREMENT_EXPERIENCE_REGEX,
                RegexProperty.PROPERTY_REQUIREMENT_EXPERIENCE_REGEX);
        request.setAttribute(CommandAttribute.REQUIREMENT_SALARY_REGEX,
                RegexProperty.PROPERTY_REQUIREMENT_SALARY_REGEX);
        request.setAttribute(CommandAttribute.REQUIREMENT_QUALIFICATION_REGEX,
                RegexProperty.PROPERTY_REQUIREMENT_QUALIFICATION_REGEX);
        request.setAttribute(CommandAttribute.REQUIREMENT_COMMENT_REGEX,
                RegexProperty.PROPERTY_REQUIREMENT_COMMENT_REGEX);
        request.setAttribute(CommandAttribute.REQUIREMENT_PRIMARY_REGEX,
                RegexProperty.PROPERTY_REQUIREMENT_PRIMARY_REGEX);

        request.setAttribute(RequestParameter.TECHNICAL_TASKS, technicalTasks);
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.REQUIREMENT_CREATION_JSP);
        return router;
    }

}
