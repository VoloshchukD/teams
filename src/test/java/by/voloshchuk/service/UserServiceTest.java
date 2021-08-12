package by.voloshchuk.service;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.dto.UserDto;
import by.voloshchuk.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {

    private static UserService userService;

    private static UserDto userDto;

    private static EmployeeRequirement requirement;

    private static final Long DATABASE_USER_ID = 1L;

    private static final Long DATABASE_USER_DETAIL_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        CustomConnectionPool pool = CustomConnectionPool.getInstance();
        userService = ServiceProvider.getInstance().getUserService();
        userDto = new UserDto();
        userDto.setUserId(DATABASE_USER_ID);
        userDto.setEmail("smith11@gmail.com");
        userDto.setPassword("Qwerty1234");
        userDto.setRole(User.UserRole.DEVELOPER.toString());
        userDto.setUserDetailId(DATABASE_USER_DETAIL_ID);
        userDto.setSalary("100");
        userDto.setExperience("40");
        userDto.setPrimarySkill("C");
        userDto.setCompany("Oracle");
        userDto.setFirstName("John");
        userDto.setLastName("Wick");
        userDto.setPosition("Team Lead");
        userDto.setRole("DEVELOPER");
        userDto.setPrimarySkill("Python");
        userDto.setSkillsDescription("-");

        requirement = new EmployeeRequirement();
        requirement.setSalary(1000);
        requirement.setPrimarySkill("C");
        requirement.setExperience(15);
    }

    @Test
    public void testAddUser() throws ServiceException {
        boolean added = userService.addUser(userDto);
        Assert.assertTrue(added);
    }

    @Test
    public void testCheckUser() throws ServiceException {
        User foundedUser = userService.checkUser(userDto.getEmail(), userDto.getPassword());
        Assert.assertNotNull(foundedUser);
    }

    @Test
    public void testFindUsersByEmployeeRequirement() throws ServiceException {
        List<User> foundedUsers = userService.findAllByEmployeeRequirement(requirement);
        Assert.assertNotNull(foundedUsers);
    }

    @Test
    public void testAddUserToProject() throws ServiceException {
        boolean added = userService.addUserToProject(DATABASE_USER_ID, DATABASE_PROJECT_ID);
        Assert.assertTrue(added);
    }

}
