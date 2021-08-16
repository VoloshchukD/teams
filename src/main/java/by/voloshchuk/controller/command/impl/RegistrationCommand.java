package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
import by.voloshchuk.util.RequestParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to sign up user.
 *
 * @author Daniil Voloshchuk
 */
public class RegistrationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    private static final String DEFAULT_USER_IMAGE = "/images/logo.png";

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        UserDto userDto = RequestParser.buildUserDto(request);
        UserService userService = serviceProvider.getUserService();
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.REGISTRATION_JSP);
        try {
            if (userService.addUser(userDto)) {
                request.getSession().setAttribute(CommandAttribute.USER_ID,
                        userDto.getUserId());
                request.getSession().setAttribute(CommandAttribute.USER_DETAIL_ID,
                        userDto.getUserDetailId());
                request.getSession().setAttribute(CommandAttribute.USER_IMAGE,
                        DEFAULT_USER_IMAGE);
                User.UserRole role = User.UserRole.valueOf(userDto.getRole());
                request.getSession().setAttribute(CommandAttribute.ROLE, role);
                router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                        CommandPath.TO_PROJECTS);
            } else {
                request.setAttribute(CommandAttribute.ERROR, true);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        return router;
    }

}
