package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
import by.voloshchuk.servlet.command.AsyncCommand;
import by.voloshchuk.servlet.command.RequestParameter;
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

public class LoadProjectUsersCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long projectId = Long.parseLong(request.getParameter(RequestParameter.PROJECT_ID));
        List<User> users = null;
        UserService userService = serviceProvider.getUserService();
        try {
            users = userService.findUsersByProjectId(projectId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }


        JSONArray data = new JSONArray();
        for (User user : users){
            JSONObject currentData = new JSONObject();
            UserDetail userDetail = user.getUserDetail();
            currentData.put("id", user.getId());
            currentData.put("forename", userDetail.getFirstName());
            currentData.put("surname", userDetail.getLastName());
            currentData.put("avatar", userDetail.getImagePath());
            data.put(currentData);
        }
        response.getWriter().write(data.toString());
    }

}
