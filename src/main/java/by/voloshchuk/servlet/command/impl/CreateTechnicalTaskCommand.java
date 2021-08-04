package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TechnicalTaskService;
import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandAttribute;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.CommandRouter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTechnicalTaskCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        TechnicalTaskService technicalTaskService = serviceProvider.getTechnicalTaskService();
        try {
            TechnicalTask technicalTask = createTechnicalTask(request);
            technicalTaskService.addTechnicalTask(technicalTask);
        } catch (ServiceException | ParseException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_CREATE_REQUIREMENT);
        return router;
    }

    private TechnicalTask createTechnicalTask(HttpServletRequest request) throws ParseException {
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setName(request.getParameter("name"));
        technicalTask.setOverview(request.getParameter("overview"));
        String deadlineAsString = request.getParameter("deadline");
        Date deadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(deadlineAsString);
        technicalTask.setDeadline(deadline);
        technicalTask.setStatus(TechnicalTask.TechnicalTaskStatus.EDITING);
        User user = new User();
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        user.setId(userId);
        technicalTask.setCustomer(user);
        return technicalTask;
    }

}
