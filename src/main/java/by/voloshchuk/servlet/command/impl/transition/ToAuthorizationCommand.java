package by.voloshchuk.servlet.command.impl.transition;

import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.SessionAttribute;
import by.voloshchuk.util.RegexProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToAuthorizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(SessionAttribute.EMAIL_REGEX, RegexProperty.PROPERTY_EMAIL_REGEX);
        request.setAttribute(SessionAttribute.PASSWORD_REGEX, RegexProperty.PROPERTY_PASSWORD_REGEX);
        request.getRequestDispatcher(CommandPath.AUTHORIZATION_JSP).forward(request, response);
    }

}
