package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.TechnicalTask;
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
import java.util.ArrayList;
import java.util.List;

public class TechnicalTasksCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        int currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE));
        int projectsPerPage = Integer.parseInt(request.getParameter(RequestParameter.TECHNICAL_TASKS_PER_PAGE));
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        List<TechnicalTask> technicalTasks = null;
        TechnicalTaskService technicalTaskService = serviceProvider.getTechnicalTaskService();
        try {
            technicalTasks = technicalTaskService.findTechnicalTasksByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        List<TechnicalTask> pageUnits = new ArrayList<>();
        int start = (currentPage - 1) * projectsPerPage;
        for (int i = start; i < start + projectsPerPage; i++) {
            if (i == technicalTasks.size()) {
                break;
            }
            pageUnits.add(technicalTasks.get(i));
        }

        request.setAttribute(RequestParameter.TECHNICAL_TASKS, pageUnits);

        int pagesNumber = technicalTasks.size() / projectsPerPage;
        if (technicalTasks.size() % projectsPerPage != 0) {
            pagesNumber++;
        }
        request.setAttribute(RequestParameter.ALL_PAGES, pagesNumber);
        request.setAttribute(RequestParameter.CURRENT_PAGE, currentPage);
        request.setAttribute(RequestParameter.TECHNICAL_TASKS_PER_PAGE, projectsPerPage);

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.TECHNICAL_TASKS_JSP);
        return router;
    }

}
