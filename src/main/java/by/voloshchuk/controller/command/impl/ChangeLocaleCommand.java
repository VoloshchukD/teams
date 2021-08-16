package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.controller.command.RequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to change locale.
 *
 * @author Daniil Voloshchuk
 */
public class ChangeLocaleCommand implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(true).setAttribute(CommandAttribute.LOCAL,
                request.getParameter(CommandAttribute.LOCAL));
        String referer = request.getHeader(RequestParameter.REFER_HEADER);
        CommandRouter router =
                new CommandRouter(CommandRouter.RouterType.REDIRECT, referer);
        return router;
    }

}
