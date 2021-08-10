package by.voloshchuk.controller.command.impl;

import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MainCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Map<String, Integer> resultData = null;
        UserService userService = serviceProvider.getUserService();
        try {
            resultData = userService.findBasicData();
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        request.setAttribute(CommandAttribute.CUSTOMERS_AMOUNT, resultData.get(CommandAttribute.CUSTOMERS_AMOUNT));
        request.setAttribute(CommandAttribute.YEARS_ON_MARKET, resultData.get(CommandAttribute.YEARS_ON_MARKET));
        request.setAttribute(CommandAttribute.PROJECTS_AMOUNT, resultData.get(CommandAttribute.PROJECTS_AMOUNT));
        request.setAttribute(CommandAttribute.PROJECTS_PRODUCTIVITY, resultData.get(CommandAttribute.PROJECTS_PRODUCTIVITY));
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.MAIN_JSP);
        return router;
    }

}
