package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.util.RegexProperty;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to move to profile page.
 *
 * @author Daniil Voloshchuk
 */
public class ToProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        UserDetail userDetail = null;
        UserDetailService userDetailService = serviceProvider.getUserDetailService();
        try {
            userDetail = userDetailService.findUserDetailByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(CommandAttribute.USER_DATA, userDetail);
        request.setAttribute(CommandAttribute.FIRST_REGEX, RegexProperty.PROPERTY_FIRST_REGEX);
        request.setAttribute(CommandAttribute.LAST_REGEX, RegexProperty.PROPERTY_LAST_REGEX);
        request.setAttribute(CommandAttribute.COMPANY_REGEX, RegexProperty.PROPERTY_COMPANY_REGEX);
        request.setAttribute(CommandAttribute.POSITION_REGEX, RegexProperty.PROPERTY_POSITION_REGEX);
        request.setAttribute(CommandAttribute.EXPERIENCE_REGEX, RegexProperty.PROPERTY_EXPERIENCE_REGEX);
        request.setAttribute(CommandAttribute.SALARY_REGEX, RegexProperty.PROPERTY_SALARY_REGEX);
        request.setAttribute(CommandAttribute.PRIMARY_REGEX, RegexProperty.PROPERTY_PRIMARY_REGEX);
        request.setAttribute(CommandAttribute.SKILLS_REGEX, RegexProperty.PROPERTY_SKILLS_REGEX);
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.PROFILE_JSP);
        return router;
    }

}
