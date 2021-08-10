package by.voloshchuk.controller.command.impl;

import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TechnicalTaskService;
import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.controller.command.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTechnicalTaskCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        TechnicalTaskService technicalTaskService = serviceProvider.getTechnicalTaskService();
        TechnicalTask technicalTask = createTechnicalTask(request);
        try {
            technicalTaskService.updateTechnicalTask(technicalTask);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.TECHNICAL_TASKS);
        return router;
    }

    private TechnicalTask createTechnicalTask(HttpServletRequest request) {
        TechnicalTask technicalTask = new TechnicalTask();
        Long technicalTaskId = Long.parseLong(request.getParameter(RequestParameter.TECHNICAL_TASK_ID));
        technicalTask.setId(technicalTaskId);
        technicalTask.setName(request.getParameter(RequestParameter.TECHNICAL_TASKS_NAME));
        technicalTask.setOverview(request.getParameter(RequestParameter.TECHNICAL_TASKS_OVERVIEW));
        return technicalTask;
    }

}
