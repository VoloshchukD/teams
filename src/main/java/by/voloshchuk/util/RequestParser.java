package by.voloshchuk.util;

import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.RequestParameter;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Class with creating entity from request .
 *
 * @author Daniil Voloshchuk
 */
public class RequestParser {

    public static UserDto buildUserDto(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        userDto.setEmail(request.getParameter(RequestParameter.EMAIL));
        userDto.setPassword(request.getParameter(RequestParameter.PASSWORD));
        userDto.setRole(request.getParameter(RequestParameter.ROLE));
        userDto.setFirstName(request.getParameter(RequestParameter.FIRST_NAME));
        userDto.setLastName(request.getParameter(RequestParameter.LAST_NAME));
        userDto.setCompany(request.getParameter(RequestParameter.COMPANY));
        userDto.setPosition(request.getParameter(RequestParameter.POSITION));
        userDto.setExperience(request.getParameter(RequestParameter.EXPERIENCE));
        userDto.setSalary(request.getParameter(RequestParameter.SALARY));
        userDto.setPrimarySkill(request.getParameter(RequestParameter.PRIMARY_SKILL));
        userDto.setSkillsDescription(request.getParameter(RequestParameter.SKILLS_DESCRIPTION));
        return userDto;
    }

    public static EmployeeRequirementDto buildEmployeeRequirementDto(HttpServletRequest request) {
        EmployeeRequirementDto employeeRequirementDto = new EmployeeRequirementDto();
        employeeRequirementDto.setPrimarySkill(request.getParameter(RequestParameter.PRIMARY_SKILL));
        employeeRequirementDto.setSalary(request.getParameter(RequestParameter.SALARY));
        employeeRequirementDto.setExperience(request.getParameter(RequestParameter.EXPERIENCE));
        employeeRequirementDto.setQualification(request.getParameter(RequestParameter.QUALIFICATION));
        employeeRequirementDto.setComment(request.getParameter(RequestParameter.COMMENT));
        return employeeRequirementDto;
    }

    public static TaskDto buildTaskDto(HttpServletRequest request) {
        TaskDto taskDto = new TaskDto();
        taskDto.setUserId(request.getParameter(AsyncCommandParameter.USER_ID));
        taskDto.setName(request.getParameter(AsyncCommandParameter.TASK_NAME));
        taskDto.setDetails(request.getParameter(AsyncCommandParameter.TASK_DETAILS));
        taskDto.setPlannedTime(request.getParameter(AsyncCommandParameter.TASK_HOURS));
        return taskDto;
    }

    public static BillDto buildBillDto(HttpServletRequest request) {
        BillDto billDto = new BillDto();
        billDto.setAmountDue(request.getParameter(RequestParameter.BILL_AMOUNT));
        billDto.setInformation(request.getParameter(RequestParameter.BILL_INFORMATION));
        billDto.setProjectId(request.getParameter(RequestParameter.PROJECT_ID));
        return billDto;
    }

    public static ProjectDto buildProjectDto(HttpServletRequest request) {
        ProjectDto projectDto = new ProjectDto();
        Project project = new Project();
        project.setName(request.getParameter(RequestParameter.PROJECT_NAME));
        project.setDescription(request.getParameter(RequestParameter.PROJECT_DESCRIPTION));
        project.setState(Project.ProjectStatus.IN_PROGRESS);
        project.setTechnicalTaskId(
                Long.parseLong(request.getParameter(RequestParameter.TECHNICAL_TASK_ID)));
        projectDto.setProject(project);
        projectDto.setCustomerId(
                Long.parseLong(request.getParameter(RequestParameter.CUSTOMER_ID)));
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        projectDto.setManagerId(userId);
        return projectDto;
    }

    public static TechnicalTaskDto buildTechnicalTaskDto(HttpServletRequest request) {
        TechnicalTaskDto technicalTaskDto = new TechnicalTaskDto();
        technicalTaskDto.setName(request.getParameter(RequestParameter.TECHNICAL_TASKS_NAME));
        technicalTaskDto.setOverview(request.getParameter(RequestParameter.TECHNICAL_TASKS_OVERVIEW));
        technicalTaskDto.setDeadline(request.getParameter(RequestParameter.TECHNICAL_TASKS_DEADLINE));
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        technicalTaskDto.setCustomerId(userId);
        return technicalTaskDto;
    }

    public static PaymentDto buildPaymentDto(HttpServletRequest request) {
        PaymentDto paymentDto = new PaymentDto();
        Long billId = Long.parseLong(request.getParameter(RequestParameter.BILL_ID));
        paymentDto.setBillId(billId);
        paymentDto.setCardHolder(request.getParameter(RequestParameter.CARD_HOLDER));
        paymentDto.setMonth(request.getParameter(RequestParameter.CARD_MONTH));
        paymentDto.setYear(request.getParameter(RequestParameter.CARD_YEAR));
        paymentDto.setNumber(request.getParameter(RequestParameter.CARD_NUMBER));
        paymentDto.setCvc(request.getParameter(RequestParameter.CARD_CVC));
        return paymentDto;
    }

}
