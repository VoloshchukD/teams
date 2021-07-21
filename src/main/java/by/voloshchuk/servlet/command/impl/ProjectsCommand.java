package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.impl.ProjectServiceImpl;
import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.RequestParameter;
import by.voloshchuk.servlet.command.CommandAttribute;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private ProjectService projectService = new ProjectServiceImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE));
        int projectsPerPage = Integer.parseInt(request.getParameter(RequestParameter.PROJECTS_PER_PAGE));
        String state = request.getParameter(RequestParameter.PROJECT_STATE);
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        List<Project> projects = null;
        try {
            projects = projectService.findProjectsByUserIdAndState(userId, state);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        List<Project> pageProjects = new ArrayList<>();
        int start = (currentPage - 1) * projectsPerPage;
        for (int i = start; i < start + projectsPerPage; i++) {
            if (i == projects.size()) {
                break;
            }
            pageProjects.add(projects.get(i));
        }

        request.setAttribute(RequestParameter.PROJECTS, pageProjects);

        int pagesNumber = projects.size() / projectsPerPage;
        if (projects.size() % projectsPerPage != 0) {
            pagesNumber++;
        }
        request.setAttribute(RequestParameter.ALL_PAGES, pagesNumber);
        request.setAttribute(RequestParameter.CURRENT_PAGE, currentPage);
        request.setAttribute(RequestParameter.PROJECT_STATE, state);
        request.setAttribute(RequestParameter.PROJECTS_PER_PAGE, projectsPerPage);

        request.getRequestDispatcher(CommandPath.PROJECTS_JSP).forward(request, response);
    }

}
