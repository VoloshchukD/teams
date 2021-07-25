package by.voloshchuk.servlet.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException;

}
