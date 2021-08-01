package by.voloshchuk.servlet.command.impl.transition;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.servlet.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToSeekEmployeesCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Long technicalTaskId = Long.parseLong(request.getParameter(RequestParameter.TECHNICAL_TASK_ID));
        List<EmployeeRequirement> employeeRequirements = null;
        EmployeeRequirementService employeeRequirementService = serviceProvider.getEmployeeRequirementService();
        try {
            employeeRequirements = employeeRequirementService.findAllByTechnicalTaskId(technicalTaskId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }

        request.setAttribute(CommandAttribute.EMPLOYEE_REQUIREMENTS, employeeRequirements);

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.SEEK_EMPLOYEES_JSP);
        return router;
    }

}
