package by.voloshchuk.service;

import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.entity.dto.BillDto;
import by.voloshchuk.exception.ServiceException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class BillServiceTest {

    private static BillService billService;

    private static Bill bill;

    private static final Long DATABASE_BILL_ID = 1L;

    private static final Long DATABASE_PROJECT_ID = 1L;

    private static final Long DATABASE_USER_ID = 1L;

    @BeforeClass
    public static void initializeTestData() {
        CustomConnectionPool pool = CustomConnectionPool.getInstance();
        billService = ServiceProvider.getInstance().getBillService();
        bill = new Bill();
        bill.setId(DATABASE_BILL_ID);
        bill.setProjectId(DATABASE_PROJECT_ID);
        bill.setStatus(Bill.BillStatus.PAID);
        bill.setInformation("bill info");
        bill.setAmountDue(999);
    }

    @Test
    public void testAddBill() throws ServiceException {
        BillDto billDto = new BillDto();
        billDto.setAmountDue(bill.getAmountDue().toString());
        billDto.setInformation(bill.getInformation());
        billDto.setStatus(bill.getStatus().toString());
        billDto.setProjectId(bill.getProjectId().toString());
        boolean added = billService.addBill(billDto);
        Assert.assertTrue(added);
    }

    @Test
    public void testFindBillById() throws ServiceException {
        Bill founded = billService.findBillByIdAndUserId(DATABASE_BILL_ID, DATABASE_USER_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindBillsByProjectId() throws ServiceException {
        List<Bill> founded = billService.findBillsByProjectId(DATABASE_PROJECT_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testFindBillsByUserId() throws ServiceException {
        List<Bill> founded = billService.findBillsByUserId(DATABASE_USER_ID);
        Assert.assertNotNull(founded);
    }

    @Test
    public void testUpdateBill() throws ServiceException {
        String updateData = "updated bill data";
        bill.setInformation(updateData);
        Bill updated = billService.updateBill(bill);
        Assert.assertEquals(updateData, updated.getInformation());
    }

}
