package by.voloshchuk.servlet.command.impl.async;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.TechnicalTask;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.service.UserService;
import by.voloshchuk.servlet.command.AsyncCommand;
import by.voloshchuk.servlet.command.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddRequirementCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        EmployeeRequirement employeeRequirement = createEmployeeRequirement(request);
        EmployeeRequirementService employeeRequirementService = serviceProvider.getEmployeeRequirementService();
        boolean result = false;
        try {
            result = employeeRequirementService.addEmployeeRequirement(employeeRequirement);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        System.out.println(employeeRequirement);
    }

    private EmployeeRequirement createEmployeeRequirement(HttpServletRequest request) {
        EmployeeRequirement employeeRequirement = new EmployeeRequirement();
        Long technicalTaskId = Long.parseLong(request.getParameter(RequestParameter.TECHNICAL_TASK_ID));
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setId(technicalTaskId);
        employeeRequirement.setTechnicalTask(technicalTask);
        employeeRequirement.setPrimarySkill(request.getParameter(RequestParameter.PRIMARY_SKILL));
        employeeRequirement.setSalary(Integer.parseInt(request.getParameter(RequestParameter.SALARY)));
        employeeRequirement.setExperience(Integer.parseInt(request.getParameter(RequestParameter.EXPERIENCE)));
        employeeRequirement.setQualification(request.getParameter(RequestParameter.QUALIFICATION));
        employeeRequirement.setComment(request.getParameter(RequestParameter.COMMENT));
        return employeeRequirement;
    }

}
