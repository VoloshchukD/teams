package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.servlet.command.AsyncCommand;
import by.voloshchuk.servlet.command.AsyncCommandParameter;
import by.voloshchuk.servlet.command.CommandAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
