package by.voloshchuk.util;

import by.voloshchuk.controller.command.AsyncCommandParameter;
import by.voloshchuk.controller.command.RequestParameter;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.entity.dto.EmployeeRequirementDto;
import by.voloshchuk.entity.dto.TaskDto;
import by.voloshchuk.entity.dto.UserDto;

import javax.servlet.http.HttpServletRequest;

public class DtoBuilder {

    public static User buildUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(User.UserRole.valueOf(userDto.getRole()));
        user.setUserDetail(buildUserDetail(userDto));
        return user;
    }

    public static UserDetail buildUserDetail(UserDto userDto) {
        UserDetail userDetail = new UserDetail();
        userDetail.setFirstName(userDto.getFirstName());
        userDetail.setLastName(userDto.getLastName());
        userDetail.setCompany(userDto.getCompany());
        userDetail.setPosition(userDto.getPosition());
        userDetail.setExperience(Integer.parseInt(userDto.getExperience()));
        userDetail.setSalary(Integer.parseInt(userDto.getSalary()));
        userDetail.setPrimarySkill(userDto.getPrimarySkill());
        userDetail.setSkillsDescription(userDto.getSkillsDescription());
        userDetail.setStatus(UserDetail.Status.NOT_BUSY);
        return userDetail;
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

}
