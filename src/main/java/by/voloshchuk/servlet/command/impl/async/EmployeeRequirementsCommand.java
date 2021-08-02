package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserDetailService;
import by.voloshchuk.service.impl.UserDetailServiceImpl;
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

public class EmployeeRequirementsCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long technicalTaskId = Long.parseLong(request.getParameter(RequestParameter.TECHNICAL_TASK_ID));
        List<EmployeeRequirement> employeeRequirements = null;
        EmployeeRequirementService employeeRequirementService = serviceProvider.getEmployeeRequirementService();
        try {
            employeeRequirements = employeeRequirementService.findAllByTechnicalTaskId(technicalTaskId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }

        JSONArray data = new JSONArray();
        for (EmployeeRequirement employeeRequirement : employeeRequirements){
            JSONObject currentData = new JSONObject();
            currentData.put("experience", employeeRequirement.getExperience());
            currentData.put("salary", employeeRequirement.getSalary());
            currentData.put("qualification", employeeRequirement.getQualification());
            currentData.put("primary", employeeRequirement.getPrimarySkill());
            currentData.put("comment", employeeRequirement.getComment());
            data.put(currentData);
        }
        response.getWriter().write(data.toString());
    }

}
