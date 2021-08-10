package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocaleCommand implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(true).setAttribute(CommandAttribute.LOCAL, request.getParameter(CommandAttribute.LOCAL));
        String referer = request.getHeader(RequestParameter.REFER_HEADER);
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, referer);
        return router;
    }

}
