package by.voloshchuk.service.impl;

import by.voloshchuk.dao.BillDao;
import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;

import java.util.List;

public class BillServiceImpl implements BillService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public Bill findBillById(Long id) throws ServiceException {
        Bill bill = null;
        BillDao billDao = daoProvider.getBillDao();
        try {
            bill = billDao.findBillById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bill;
    }

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

    @Override
    public List<Bill> findBillsByProjectId(Long projectId) throws ServiceException {
        List<Bill> bills = null;
        BillDao billDao = daoProvider.getBillDao();
        try {
            bills = billDao.findBillsByProjectId(projectId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return bills;
    }

    @Override
    public String updateBillStatus(Long billId, String status) throws ServiceException {
        String resultStatus = null;
        BillDao billDao = daoProvider.getBillDao();
        try {
            resultStatus = billDao.updateBillStatus(billId, status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return resultStatus;
    }

}
