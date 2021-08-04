package by.voloshchuk.service.impl;

import by.voloshchuk.dao.*;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class BillServiceImpl implements BillService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public boolean addBill(Bill bill) throws ServiceException {
        boolean result = false;
        BillDao billDao = daoProvider.getBillDao();
        try {
            result = billDao.addBill(bill);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Bill> findBillsByUserId(Long userId) throws ServiceException {
        List<Bill> bills = null;
        BillDao billDao = daoProvider.getBillDao();
        try {
            bills = billDao.findBillsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bills;
    }

}
