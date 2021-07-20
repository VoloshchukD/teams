package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getSession().invalidate();
        response.sendRedirect(CommandPath.MAIN);
    }

}
