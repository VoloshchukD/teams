package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to delete users account.
 *
 * @author Daniil Voloshchuk
 */
public class DeleteAccountCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long userDetailId = (Long) request.getSession().getAttribute(
                CommandAttribute.USER_DETAIL_ID);
        String status = UserDetail.Status.DELETED.toString();

        UserDetailService userDetailService = serviceProvider.getUserDetailService();
        try {
            userDetailService.updateUserDetailStatus(userDetailId, status);
            request.getSession().invalidate();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.getSession().invalidate();
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.MAIN);
        return router;
    }

}
