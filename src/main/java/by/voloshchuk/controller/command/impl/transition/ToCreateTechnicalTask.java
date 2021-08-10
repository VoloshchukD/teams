package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.util.RegexProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToCreateTechnicalTask implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute(CommandAttribute.TECHNICAL_TASK_NAME_REGEX,
                RegexProperty.PROPERTY_TECHNICAL_TASK_NAME_REGEX);
        request.setAttribute(CommandAttribute.TECHNICAL_TASK_OVERVIEW_REGEX,
                RegexProperty.PROPERTY_TECHNICAL_TASK_OVERVIEW_REGEX);
        request.setAttribute(CommandAttribute.TECHNICAL_TASK_DEADLINE_REGEX,
                RegexProperty.PROPERTY_TECHNICAL_TASK_DEADLINE_REGEX);

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.CREATE_TECHNICAL_TASKS_JSP);
        return router;
    }

}
