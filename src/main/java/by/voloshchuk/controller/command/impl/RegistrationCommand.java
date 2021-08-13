package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
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
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        UserDto userDto = buildDto(request);
        UserService userService = serviceProvider.getUserService();
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.REGISTRATION_JSP);
        try {
            if (userService.addUser(userDto)) {
                request.getSession().setAttribute(CommandAttribute.USER_ID, userDto.getUserId());
                request.getSession().setAttribute(CommandAttribute.USER_DETAIL_ID, userDto.getUserDetailId());
                request.getSession().setAttribute(CommandAttribute.USER_IMAGE, DEFAULT_USER_IMAGE);
                User.UserRole role = User.UserRole.valueOf(userDto.getRole());
                request.getSession().setAttribute(CommandAttribute.ROLE, role);
                router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_PROJECTS);
            } else {
                request.setAttribute(CommandAttribute.ERROR, true);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        return router;
    }

    private UserDto buildDto(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setEmail(request.getParameter(RequestParameter.EMAIL));
        userDto.setPassword(request.getParameter(RequestParameter.PASSWORD));
        userDto.setRole(request.getParameter(RequestParameter.ROLE));
        userDto.setFirstName(request.getParameter(RequestParameter.FIRST_NAME));
        userDto.setLastName(request.getParameter(RequestParameter.LAST_NAME));
        userDto.setCompany(request.getParameter(RequestParameter.COMPANY));
        userDto.setPosition(request.getParameter(RequestParameter.POSITION));
        userDto.setExperience(request.getParameter(RequestParameter.EXPERIENCE));
        userDto.setSalary(request.getParameter(RequestParameter.SALARY));
        userDto.setPrimarySkill(request.getParameter(RequestParameter.PRIMARY_SKILL));
        userDto.setSkillsDescription(request.getParameter(RequestParameter.SKILLS_DESCRIPTION));
        return userDto;
    }

}
