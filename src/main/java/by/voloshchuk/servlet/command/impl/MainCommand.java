package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.UserService;
import by.voloshchuk.service.impl.UserServiceImpl;
import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandAttribute;
import by.voloshchuk.servlet.command.CommandPath;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MainCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private UserService userService = new UserServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, Integer> resultData = null;
        try {
            resultData = userService.findBasicData();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(CommandAttribute.CUSTOMERS_AMOUNT, resultData.get(CommandAttribute.CUSTOMERS_AMOUNT));
        request.setAttribute(CommandAttribute.YEARS_ON_MARKET, resultData.get(CommandAttribute.YEARS_ON_MARKET));
        request.setAttribute(CommandAttribute.PROJECTS_AMOUNT, resultData.get(CommandAttribute.PROJECTS_AMOUNT));
        request.setAttribute(CommandAttribute.PROJECTS_PRODUCTIVITY, resultData.get(CommandAttribute.PROJECTS_PRODUCTIVITY));

        request.getRequestDispatcher(CommandPath.MAIN_JSP).forward(request, response);
    }

}
