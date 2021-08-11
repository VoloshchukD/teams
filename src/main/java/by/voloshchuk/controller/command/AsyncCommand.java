package by.voloshchuk.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface for async commands.
 *
 * @author Daniil Voloshchuk
 */
public interface AsyncCommand {

    /**
     * Method for async command logics.
     *
     * @param request  - users request
     * @param response - data to return to user
     */
    void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;

}
