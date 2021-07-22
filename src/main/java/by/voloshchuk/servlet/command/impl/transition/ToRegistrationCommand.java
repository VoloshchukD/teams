package by.voloshchuk.servlet.command.impl.transition;

import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.CommandAttribute;
import by.voloshchuk.servlet.command.CommandRouter;
import by.voloshchuk.util.RegexProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRegistrationCommand implements Command {

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(CommandAttribute.EMAIL_REGEX, RegexProperty.PROPERTY_EMAIL_REGEX);
        request.setAttribute(CommandAttribute.PASSWORD_REGEX, RegexProperty.PROPERTY_PASSWORD_REGEX);
        request.setAttribute(CommandAttribute.FIRST_REGEX, RegexProperty.PROPERTY_FIRST_REGEX);
        request.setAttribute(CommandAttribute.LAST_REGEX, RegexProperty.PROPERTY_LAST_REGEX);
        request.setAttribute(CommandAttribute.COMPANY_REGEX, RegexProperty.PROPERTY_COMPANY_REGEX);
        request.setAttribute(CommandAttribute.POSITION_REGEX, RegexProperty.PROPERTY_POSITION_REGEX);
        request.setAttribute(CommandAttribute.EXPERIENCE_REGEX, RegexProperty.PROPERTY_EXPERIENCE_REGEX);
        request.setAttribute(CommandAttribute.SALARY_REGEX, RegexProperty.PROPERTY_SALARY_REGEX);
        request.setAttribute(CommandAttribute.PRIMARY_REGEX, RegexProperty.PROPERTY_PRIMARY_REGEX);
        request.setAttribute(CommandAttribute.SKILLS_REGEX, RegexProperty.PROPERTY_SKILLS_REGEX);
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.REGISTRATION_JSP);
        return router;
    }

}
