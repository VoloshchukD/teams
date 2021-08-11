package by.voloshchuk.controller;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandProvider;
import by.voloshchuk.controller.command.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for processing users async requests.
 *
 * @author Daniil Voloshchuk
 */
public class AsyncController extends HttpServlet {

    private static final AsyncCommandProvider asyncProvider = AsyncCommandProvider.getInstance();

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

    /**
     * Method with command processing.
     *
     * @param request  - users request
     * @param response - data to return to user
     */
    private void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        AsyncCommand asyncCommand = asyncProvider.getCommand(commandName);
        asyncCommand.execute(request, response);
    }

}
