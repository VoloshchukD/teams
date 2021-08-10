package by.voloshchuk.controller.command.impl;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.EmployeeRequirementService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.controller.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateRequirementCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        EmployeeRequirementService requirementService = serviceProvider.getEmployeeRequirementService();
        EmployeeRequirement requirement = createEmployeeRequirement(request);
        try {
            requirementService.updateEmployeeRequirement(requirement);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.REQUIREMENTS + request.getParameter(RequestParameter.TECHNICAL_TASK_ID));
        return router;
    }

    private EmployeeRequirement createEmployeeRequirement(HttpServletRequest request) {
        EmployeeRequirement requirement = new EmployeeRequirement();
        Long requirementId = Long.parseLong(request.getParameter(RequestParameter.REQUIREMENT_ID));
        requirement.setId(requirementId);
        requirement.setQualification(request.getParameter(RequestParameter.REQUIREMENT_ID));
        requirement.setExperience(
                Integer.parseInt(request.getParameter(RequestParameter.REQUIREMENT_EXPERIENCE)));
        requirement.setSalary(
                Integer.parseInt(request.getParameter(RequestParameter.REQUIREMENT_SALARY)));
        requirement.setComment(request.getParameter(RequestParameter.REQUIREMENT_COMMENT));
        requirement.setPrimarySkill(request.getParameter(RequestParameter.REQUIREMENT_PRIMARY_SKILL));
        return requirement;
    }

}
