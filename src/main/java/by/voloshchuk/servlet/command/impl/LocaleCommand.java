package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.RequestParameter;
import by.voloshchuk.servlet.command.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocaleCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession(true).setAttribute(SessionAttribute.LOCAL, request.getParameter(SessionAttribute.LOCAL));
        String referer = request.getHeader(RequestParameter.REFER_HEADER);
        response.sendRedirect(referer);
    }

}
