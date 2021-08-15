package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to logout user.
 *
 * @author Daniil Voloshchuk
 */
public class LogOutCommand implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession().invalidate();
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.MAIN);
        return router;
    }

}
