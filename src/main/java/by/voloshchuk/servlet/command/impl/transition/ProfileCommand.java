package by.voloshchuk.servlet.command.impl.transition;

import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.CommandRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileCommand implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.PROFILE_JSP);
        return router;
    }

}
