package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.controller.command.CommandAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Async command for updating user status.
 *
 * @author Daniil Voloshchuk
 */
public class UpdateUserStatusCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String status = request.getParameter(AsyncCommandParameter.USER_DETAIL_STATUS);
        Long userDetailId = (Long) request.getSession().getAttribute(CommandAttribute.USER_DETAIL_ID);

        UserDetailService userDetailService = serviceProvider.getUserDetailService();
        try {
            userDetailService.updateUserDetailStatus(userDetailId, status);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

}
