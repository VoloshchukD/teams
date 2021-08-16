package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to move to about page.
 *
 * @author Daniil Voloshchuk
 */
public class ToAboutCommand implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.ABOUT_JSP);
        return router;
    }

}
