package by.voloshchuk.servlet.command.impl.transition;

import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.SessionAttribute;
import by.voloshchuk.util.RegexProperty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(SessionAttribute.EMAIL_REGEX, RegexProperty.PROPERTY_EMAIL_REGEX);
        request.setAttribute(SessionAttribute.PASSWORD_REGEX, RegexProperty.PROPERTY_PASSWORD_REGEX);
        request.setAttribute(SessionAttribute.FIRST_REGEX, RegexProperty.PROPERTY_FIRST_REGEX);
        request.setAttribute(SessionAttribute.LAST_REGEX, RegexProperty.PROPERTY_LAST_REGEX);
        request.setAttribute(SessionAttribute.COMPANY_REGEX, RegexProperty.PROPERTY_COMPANY_REGEX);
        request.setAttribute(SessionAttribute.POSITION_REGEX, RegexProperty.PROPERTY_POSITION_REGEX);
        request.setAttribute(SessionAttribute.EXPERIENCE_REGEX, RegexProperty.PROPERTY_EXPERIENCE_REGEX);
        request.setAttribute(SessionAttribute.SALARY_REGEX, RegexProperty.PROPERTY_SALARY_REGEX);
        request.setAttribute(SessionAttribute.PRIMARY_REGEX, RegexProperty.PROPERTY_PRIMARY_REGEX);
        request.setAttribute(SessionAttribute.SKILLS_REGEX, RegexProperty.PROPERTY_SKILLS_REGEX);
        request.getRequestDispatcher(CommandPath.REGISTRATION_JSP).forward(request, response);
    }

}
