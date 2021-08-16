package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Async command for loading all project users data.
 *
 * @author Daniil Voloshchuk
 */
public class LoadProjectUsersCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        Long projectId = Long.parseLong(request.getParameter(AsyncCommandParameter.PROJECT_ID));
        List<User> users = null;
        UserService userService = serviceProvider.getUserService();
        try {
            users = userService.findUsersByProjectId(projectId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }

        JSONArray data = new JSONArray();
        for (User user : users) {
            JSONObject currentData = new JSONObject();
            UserDetail userDetail = user.getUserDetail();
            currentData.put(AsyncCommandParameter.USER_ID,
                    user.getId());
            currentData.put(AsyncCommandParameter.USER_DETAIL_PRIMARY_SKILL,
                    userDetail.getPrimarySkill());
            currentData.put(AsyncCommandParameter.USER_DETAIL_EXPERIENCE,
                    userDetail.getExperience());
            currentData.put(AsyncCommandParameter.USER_DETAIL_SALARY,
                    userDetail.getSalary());
            currentData.put(AsyncCommandParameter.USER_DETAIL_SKILLS_DESCRIPTION,
                    userDetail.getSkillsDescription());
            currentData.put(AsyncCommandParameter.USER_DETAIL_FIRST_NAME,
                    userDetail.getFirstName());
            currentData.put(AsyncCommandParameter.USER_DETAIL_LAST_NAME,
                    userDetail.getLastName());
            currentData.put(AsyncCommandParameter.USER_DETAIL_AVATAR,
                    userDetail.getImagePath());
            data.put(currentData);
        }
        response.getWriter().write(data.toString());
    }

}
