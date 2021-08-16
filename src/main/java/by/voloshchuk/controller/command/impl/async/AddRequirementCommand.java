package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.RequestParameter;
import by.voloshchuk.entity.dto.EmployeeRequirementDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.util.RequestParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Async command for adding requirement to technical task.
 *
 * @author Daniil Voloshchuk
 */
public class AddRequirementCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        EmployeeRequirementDto employeeRequirementDto =
                RequestParser.buildEmployeeRequirementDto(request);
        String technicalTaskId = request.getParameter(RequestParameter.TECHNICAL_TASK_ID);
        employeeRequirementDto.setTechnicalTaskId(technicalTaskId);
        EmployeeRequirementService employeeRequirementService =
                serviceProvider.getEmployeeRequirementService();
        try {
            employeeRequirementService.addEmployeeRequirement(employeeRequirementDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
    }

}
