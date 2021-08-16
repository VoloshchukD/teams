package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.controller.command.AsyncCommand;
import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.ServiceProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Async command for deleting requirement from technical task.
 *
 * @author Daniil Voloshchuk
 */
public class DeleteRequirementCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        EmployeeRequirementService requirementService =
                serviceProvider.getEmployeeRequirementService();
        Long requirementId = Long.parseLong(
                request.getParameter(AsyncCommandParameter.REQUIREMENT_ID));
        try {
            requirementService.removeEmployeeRequirement(requirementId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
    }

}
