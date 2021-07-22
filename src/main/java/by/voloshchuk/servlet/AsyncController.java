package by.voloshchuk.servlet;

import by.voloshchuk.servlet.command.AsyncCommand;
import by.voloshchuk.servlet.command.AsyncCommandProvider;
import by.voloshchuk.servlet.command.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AsyncController extends HttpServlet {

    private static final AsyncCommandProvider asyncProvider = AsyncCommandProvider.getInstance();

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.ASYNC_COMMAND);
        AsyncCommand asyncCommand = asyncProvider.getCommand(commandName);
        asyncCommand.execute(request, response);
    }

}
