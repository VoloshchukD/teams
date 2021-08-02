package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TechnicalTaskService;
import by.voloshchuk.servlet.command.*;
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
        TechnicalTask technicalTask = createTechnicalTask(request);
        TechnicalTaskService technicalTaskService = serviceProvider.getTechnicalTaskService();
        try {
            technicalTaskService.addTechnicalTask(technicalTask);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_CREATE_REQUIREMENT);
        return router;
    }

    private TechnicalTask createTechnicalTask(HttpServletRequest request) {
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setName(request.getParameter("name"));
        technicalTask.setOverview(request.getParameter("overview"));

        String deadlineAsString = request.getParameter("deadline");
        System.out.println(deadlineAsString);
        Date deadline = null;
        try {
            deadline = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(deadlineAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(deadlineAsString+"\t"+deadline);

        technicalTask.setDeadline(deadline);
        technicalTask.setStatus("EDITING");
        User user = new User();
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        user.setId(userId);
        technicalTask.setCustomer(user);
        return technicalTask;
    }

}
