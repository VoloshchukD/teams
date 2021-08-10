package by.voloshchuk.controller.command.impl;

import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.TechnicalTaskDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TechnicalTaskService;
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
            TechnicalTaskDto technicalTaskDto = createTechnicalTaskDto(request);
            technicalTaskService.addTechnicalTask(technicalTaskDto);
        } catch (ServiceException | ParseException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_CREATE_REQUIREMENT);
        return router;
    }

    private TechnicalTaskDto createTechnicalTaskDto(HttpServletRequest request) throws ParseException {
        TechnicalTaskDto technicalTaskDto = new TechnicalTaskDto();
        technicalTaskDto.setName(request.getParameter("name"));
        technicalTaskDto.setOverview(request.getParameter("overview"));
        technicalTaskDto.setDeadline(request.getParameter("deadline"));
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        technicalTaskDto.setCustomerId(userId);
        return technicalTaskDto;
    }

}
