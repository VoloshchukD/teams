package by.voloshchuk.controller.command.impl;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.dto.EmployeeRequirementDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.controller.command.*;
import by.voloshchuk.util.DtoBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to update employee requirement.
 *
 * @author Daniil Voloshchuk
 */
public class UpdateRequirementCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        EmployeeRequirementService requirementService = serviceProvider.getEmployeeRequirementService();
        EmployeeRequirementDto requirementDto = DtoBuilder.buildEmployeeRequirementDto(request);
        requirementDto.setRequirementId(Long.parseLong(request.getParameter(RequestParameter.REQUIREMENT_ID)));
        try {
            requirementService.updateEmployeeRequirement(requirementDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.REQUIREMENTS + request.getParameter(RequestParameter.TECHNICAL_TASK_ID));
        return router;
    }

}
