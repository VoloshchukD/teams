package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.util.RegexProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to move to authorization page.
 *
 * @author Daniil Voloshchuk
 */
public class ToAuthorizationCommand implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        request.setAttribute(CommandAttribute.EMAIL_REGEX,
                RegexProperty.PROPERTY_EMAIL_REGEX);
        request.setAttribute(CommandAttribute.PASSWORD_REGEX,
                RegexProperty.PROPERTY_PASSWORD_REGEX);
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.AUTHORIZATION_JSP);
        return router;
    }

}
