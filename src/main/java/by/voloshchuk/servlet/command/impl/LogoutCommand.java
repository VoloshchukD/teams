package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.CommandRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession().invalidate();
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.MAIN);
        return router;
    }

}
