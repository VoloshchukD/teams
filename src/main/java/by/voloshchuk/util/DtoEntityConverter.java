package by.voloshchuk.util;

import by.voloshchuk.entity.*;
import by.voloshchuk.entity.dto.*;

import java.text.ParseException;
import java.util.Date;

/**
 * Class with converting dto to entity logics.
 *
 * @author Daniil Voloshchuk
 */
public class DtoEntityConverter {

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

    public static Bill buildBill(BillDto billDto) {
        Bill bill = new Bill();
        bill.setAmountDue(Integer.parseInt(billDto.getAmountDue()));
        bill.setInformation(billDto.getInformation());
        bill.setStatus(Bill.BillStatus.NOT_PAID);
        bill.setProjectId(Long.parseLong(billDto.getProjectId()));
        return bill;
    }

    public static EmployeeRequirement buildEmployeeRequirement(EmployeeRequirementDto requirement) {
        EmployeeRequirement employeeRequirement = new EmployeeRequirement();
        employeeRequirement.setPrimarySkill(requirement.getPrimarySkill());
        employeeRequirement.setSalary(Integer.parseInt(requirement.getSalary()));
        employeeRequirement.setExperience(Integer.parseInt(requirement.getExperience()));
        employeeRequirement.setQualification(requirement.getQualification());
        employeeRequirement.setComment(requirement.getComment());
        return employeeRequirement;
    }

    public static Task buildTaskEntity(TaskDto taskDto) {
        Task task = new Task();
        User user = new User();
        user.setId(Long.parseLong(taskDto.getUserId()));
        task.setDeveloper(user);
        task.setName(taskDto.getName());
        task.setDetails(taskDto.getDetails());
        task.setPlannedTime(Integer.parseInt(taskDto.getPlannedTime()));
        return task;
    }

    public static TechnicalTask buildTechnicalTask(TechnicalTaskDto technicalTaskDto)
            throws ParseException {
        TechnicalTask technicalTask = new TechnicalTask();
        technicalTask.setName(technicalTaskDto.getName());
        technicalTask.setOverview(technicalTaskDto.getOverview());
        Date deadline = StringFormatter.parseDate(technicalTaskDto.getDeadline());
        technicalTask.setDeadline(deadline);
        technicalTask.setStatus(TechnicalTask.TechnicalTaskStatus.WAIT_PROJECT);
        technicalTask.setCustomerId(technicalTaskDto.getCustomerId());
        return technicalTask;
    }

}
