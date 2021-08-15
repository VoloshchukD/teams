package by.voloshchuk.service;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.UserDetail;
import by.voloshchuk.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDetailServiceTest {

    private static UserDetailService userDetailService;

    private static UserDetail userDetail;

    private static final Long DATABASE_USER_DETAIL_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        userDetailService = ServiceProvider.getInstance().getUserDetailService();
        userDetail = new UserDetail();
        userDetail.setId(DATABASE_USER_DETAIL_ID);
        userDetail.setStatus(UserDetail.Status.BUSY);
    }

    @Test
    public void testFindUserDetailsById() throws ServiceException {
        UserDetail resultUserDetail = userDetailService.findUserDetailByUserId(userDetail.getId());
        Assert.assertNotNull(resultUserDetail);
    }

    @Test
    public void testUpdateUserDetailImage() throws ServiceException {
        String updateImageValue = "/img/avatar.jpg";
        String updatedValue = userDetailService.updateUserDetailImage(userDetail.getId(), updateImageValue);
        Assert.assertNotNull(updatedValue, updateImageValue);
    }

    @Test
    public void testUpdateUserDetailStatus() throws ServiceException {
        String updateStatusValue = UserDetail.Status.NOT_BUSY.name();
        String updatedValue = userDetailService.updateUserDetailStatus(userDetail.getId(), updateStatusValue);
        Assert.assertNotNull(updatedValue, updateStatusValue);
    }

}
