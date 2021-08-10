package by.voloshchuk.controller.command.impl;

import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
import by.voloshchuk.controller.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        User user = createUser(request);
        UserService userService = serviceProvider.getUserService();
        try {
            if (userService.addUser(user)) {
                request.getSession().setAttribute(CommandAttribute.USER_ID, user.getId());
                request.getSession().setAttribute(CommandAttribute.USER_DETAIL_ID, user.getUserDetail().getId());
                request.getSession().setAttribute(CommandAttribute.ROLE, user.getRole());
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.MAIN);
        return router;
    }

    private User createUser(HttpServletRequest request) {
        User user = new User();
        user.setEmail(request.getParameter(RequestParameter.EMAIL));
        user.setPassword(request.getParameter(RequestParameter.PASSWORD));
        user.setRole(User.UserRole.valueOf(request.getParameter(RequestParameter.ROLE)));
        user.setUserDetail(createUserDetails(request));
        return user;
    }

    private UserDetail createUserDetails(HttpServletRequest request) {
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(request.getParameter(RequestParameter.FIRST_NAME));
        userDetail.setLastName(request.getParameter(RequestParameter.LAST_NAME));
        userDetail.setCompany(request.getParameter(RequestParameter.COMPANY));
        userDetail.setPosition(request.getParameter(RequestParameter.POSITION));
        userDetail.setExperience(Integer.parseInt(request.getParameter(RequestParameter.EXPERIENCE)));
        userDetail.setSalary(Integer.parseInt(request.getParameter(RequestParameter.SALARY)));
        userDetail.setPrimarySkill(request.getParameter(RequestParameter.PRIMARY_SKILL));
        userDetail.setSkillsDescription(request.getParameter(RequestParameter.SKILLS_DESCRIPTION));
        userDetail.setStatus(UserDetail.Status.NOT_BUSY);
        return userDetail;
    }

}
