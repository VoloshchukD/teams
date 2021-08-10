package by.voloshchuk.controller;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandProvider;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.controller.command.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final CommandProvider provider = CommandProvider.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = provider.getCommand(commandName);
        CommandRouter router = command.execute(request, response);
        switch (router.getRouterType()) {
            case FORWARD:
                request.getRequestDispatcher(router.getPath()).forward(request, response);
                break;
            case REDIRECT:
                response.sendRedirect(router.getPath());
                break;
        }
    }

}
