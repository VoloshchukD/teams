package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.controller.command.RequestParameter;
import by.voloshchuk.entity.dto.TaskDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.TaskService;
import by.voloshchuk.util.RequestParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to update task data.
 *
 * @author Daniil Voloshchuk
 */
public class UpdateTaskCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        TaskService taskService = serviceProvider.getTaskService();
        TaskDto taskDto = RequestParser.buildTaskDto(request);
        Long taskId = Long.parseLong(request.getParameter(RequestParameter.TASK_ID));
        taskDto.setTaskId(taskId);
        try {
            taskService.updateTask(taskDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.TASKS + request.getParameter(RequestParameter.PROJECT_ID)
                        + CommandPath.TECHNICAL_TASK_ID_PARAMETER
                        + request.getParameter(RequestParameter.TECHNICAL_TASK_ID));
        return router;
    }

}
