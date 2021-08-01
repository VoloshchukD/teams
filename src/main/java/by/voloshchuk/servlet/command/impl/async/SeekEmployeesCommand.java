package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
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

public class SeekEmployeesCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EmployeeRequirement employeeRequirement = createEmployeeRequirement(request);
        List<User> candidates = null;
        UserService userService = serviceProvider.getUserService();
        try {
            candidates = userService.findAllByEmployeeRequirement(employeeRequirement);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }

        JSONArray data = new JSONArray();
        for (User user : candidates){
            JSONObject currentData = new JSONObject();
            UserDetail userDetail = user.getUserDetail();
            currentData.put("id", user.getId());
            currentData.put("forename", userDetail.getFirstName());
            currentData.put("surname", userDetail.getLastName());
            currentData.put("avatar", userDetail.getImagePath());
            currentData.put("experience", userDetail.getExperience());
            currentData.put("salary", userDetail.getSalary());
            currentData.put("primary", userDetail.getPrimarySkill());
            currentData.put("description", userDetail.getSkillsDescription());
            data.put(currentData);
        }
        System.out.println(data.toString());
        response.getWriter().write(data.toString());
    }

    private EmployeeRequirement createEmployeeRequirement(HttpServletRequest request) {
        EmployeeRequirement employeeRequirement = new EmployeeRequirement();
        employeeRequirement.setPrimarySkill(request.getParameter(RequestParameter.PRIMARY_SKILL));
        employeeRequirement.setSalary(Integer.parseInt(request.getParameter(RequestParameter.SALARY)));
        employeeRequirement.setExperience(Integer.parseInt(request.getParameter(RequestParameter.EXPERIENCE)));
        return employeeRequirement;
    }

}
