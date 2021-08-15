package by.voloshchuk.dao;

import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDetailsDaoTest {

    private static UserDetailDao userDetailDao;

    private static UserDetail userDetail;

    @BeforeClass
    public static void initializeTestData() {
        userDetailDao = DaoProvider.getInstance().getUserDetailDao();
        userDetail = new UserDetail();
        userDetail.setId(1L);
        userDetail.setFirstName("John");
        userDetail.setLastName("Smith");
        userDetail.setCompany("Google");
        userDetail.setPosition("Team Lead");
        userDetail.setExperience(8);
        userDetail.setSalary(120);
        userDetail.setStatus(UserDetail.Status.BUSY);
    }

    @Test
    public void testFindUserDetailsById() throws DaoException {
        UserDetail resultUserDetail = userDetailDao.findUserDetailByUserId(userDetail.getId());
        Assert.assertNotNull(resultUserDetail);
    }

    @Test
    public void testUpdateUserDetail() throws DaoException {
        String updateData = "Microsoft";
        userDetail.setCompany(updateData);
        UserDetail resultUserDetail = userDetailDao.updateUserDetail(userDetail);
        Assert.assertEquals(updateData, resultUserDetail.getCompany());
    }

}
