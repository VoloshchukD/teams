package by.voloshchuk.servlet.command.impl.transition;

import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.getRequestDispatcher(CommandPath.PROFILE_JSP).forward(request, response);
    }

}
