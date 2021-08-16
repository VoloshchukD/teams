package by.voloshchuk.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for sync commands.
 *
 * @author Daniil Voloshchuk
 */
public interface Command {

    /**
     * Method for sync command logics.
     *
     * @param request  - users request
     * @param response - data to return to user
     * @return {@link CommandRouter}
     */
    CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException;

}
