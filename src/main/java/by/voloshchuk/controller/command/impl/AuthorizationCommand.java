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

public class AuthorizationCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        UserService userService = serviceProvider.getUserService();
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.AUTHORIZATION_JSP);
        try {
            User currentUser = userService.checkUser(email, password);
            if (currentUser != null &&
                    currentUser.getUserDetail().getStatus() != UserDetail.Status.DELETED) {
                request.getSession().setAttribute(CommandAttribute.USER_ID, currentUser.getId());
                request.getSession().setAttribute(CommandAttribute.USER_DETAIL_ID, currentUser.getUserDetail().getId());
                request.getSession().setAttribute(CommandAttribute.ROLE, currentUser.getRole());
                request.getSession().setAttribute(CommandAttribute.USER_IMAGE, currentUser.getUserDetail().getImagePath());
                router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_PROJECTS);
            } else {
                request.setAttribute(CommandAttribute.ERROR, true);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        return router;
    }

}
