package by.voloshchuk.dao;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class BillDaoTest {

    private static BillDao billDao;

    private static Bill bill;

    private static final Long DATABASE_BILL_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    private static final Long DATABASE_USER_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        CustomConnectionPool pool = CustomConnectionPool.getInstance();
        billDao = DaoProvider.getInstance().getBillDao();
        bill = new Bill();
        bill.setId(DATABASE_BILL_ID);
        bill.setProjectId(DATABASE_PROJECT_ID);
        bill.setStatus(Bill.BillStatus.PAID);
        bill.setInformation("bill info");
        bill.setAmountDue(999);
    }

    @Test
    public void testAddBill() throws DaoException {
        boolean added = billDao.addBill(bill);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindBillById() throws DaoException {
        Bill founded = billDao.findBillById(DATABASE_BILL_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindBillsByProjectId() throws DaoException {
        List<Bill> founded = billDao.findBillsByProjectId(DATABASE_PROJECT_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindBillsByUserId() throws DaoException {
        List<Bill> founded = billDao.findBillsByUserId(DATABASE_USER_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testUpdateBill() throws DaoException {
        String updateData = "updated bill data";
        bill.setInformation(updateData);
        Bill updated = billDao.updateBill(bill);
        Assert.assertEquals(updateData, updated.getInformation());
    }

}
