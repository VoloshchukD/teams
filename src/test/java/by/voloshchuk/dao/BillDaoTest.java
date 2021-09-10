package by.voloshchuk.dao;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

public class BillDaoTest {

    private static BillDao billDao;

    private static Bill bill;

    private static final Long DATABASE_BILL_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    private static final Long DATABASE_USER_ID = 1L;

    private static final Integer TEST_INTEGER = 1;

    private static final String TEST_STRING = "test data";

    @BeforeClass
    public static void initializeTestData() {
        billDao = DaoProvider.getInstance().getBillDao();
        bill = Mockito.mock(Bill.class);
        Mockito.when(bill.getId()).thenReturn(DATABASE_BILL_ID);
        Mockito.when(bill.getInformation()).thenReturn(TEST_STRING);
        Mockito.when(bill.getProjectId()).thenReturn(DATABASE_PROJECT_ID);
        Mockito.when(bill.getAmountDue()).thenReturn(TEST_INTEGER);
        Mockito.when(bill.getStatus()).thenReturn(Bill.BillStatus.PAID);
    }

    @Test
    public void testAddBill() throws DaoException {
        boolean added = billDao.addBill(bill);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindBillById() throws DaoException {
        Bill founded = billDao.findBillByIdAndUserId(DATABASE_BILL_ID, DATABASE_USER_ID);
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
        bill.setInformation(TEST_STRING);
        Bill updated = billDao.updateBill(bill);
        Assert.assertNotNull(updated);
    }

    @Test
    public void testUpdateBillStatus() throws DaoException {
        String updated = billDao.updateBillStatus(DATABASE_BILL_ID,
                Bill.BillStatus.ACCEPTED.toString());
        Assert.assertNotNull(updated);
    }

}
