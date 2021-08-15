package by.voloshchuk.dao;

import by.voloshchuk.entity.EmployeeRequirement;
import by.voloshchuk.entity.User;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    private static UserDao userDao;

    private static User user;

    private static EmployeeRequirement requirement;

    private static final Long DATABASE_USER_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        userDao = DaoProvider.getInstance().getUserDao();
        user = new User();
        user.setId(DATABASE_USER_ID);
        user.setEmail("smith11@gmail.com");
        user.setPassword("27389f01249t4");
        user.setRole(User.UserRole.DEVELOPER);

        UserDetail userDetail = new UserDetail();
        userDetail.setId(1L);
        userDetail.setSalary(100);
        userDetail.setExperience(40);
        userDetail.setPrimarySkill("C");
        userDetail.setFirstName("John");
        userDetail.setLastName("Smith");
        userDetail.setCompany("Google");
        userDetail.setPosition("Team Lead");
        userDetail.setStatus(UserDetail.Status.BUSY);
        user.setUserDetail(userDetail);

        requirement = new EmployeeRequirement();
        requirement.setSalary(1000);
        requirement.setPrimarySkill("C");
        requirement.setExperience(15);
    }

    @Test
    public void testAddUser() throws DaoException {
        boolean added = userDao.addUser(user);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindUserById() throws DaoException {
        User resultUser = userDao.findUserById(DATABASE_USER_ID);
        Assert.assertNotNull(resultUser);
    }

    @Test
    public void testUpdateUser() throws DaoException {
        String updateData = "Qwerty123123";
        user.setPassword(updateData);
        User updatedUser = userDao.updateUser(user);
        Assert.assertEquals(updateData, updatedUser.getPassword());
    }

    @Test
    public void testFindUserByEmail() throws DaoException {
        User foundedUser = userDao.findUserByEmail(user.getEmail());
        Assert.assertNotNull(foundedUser);
    }

    @Test
    public void testFindUsersByEmployeeRequirement() throws DaoException {
        List<User> foundedUsers = userDao.findAllByEmployeeRequirement(requirement);
        Assert.assertNotNull(foundedUsers);
    }

    @Test
    public void testAddUserToProject() throws DaoException {
        boolean added = userDao.addUserToProject(DATABASE_USER_ID, DATABASE_PROJECT_ID);
        Assert.assertTrue(added);
    }

}
