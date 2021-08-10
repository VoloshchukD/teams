package by.voloshchuk.service.impl;

import by.voloshchuk.dao.BillDao;
import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.entity.Project;
import by.voloshchuk.entity.dto.BillDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;

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
    public boolean addBill(BillDto billDto) throws ServiceException {
        boolean result = false;
        BillDao billDao = daoProvider.getBillDao();
        Validator<BillDto> billValidator = ValidatorProvider.getInstance().getBillValidator();
        if (billValidator.validateCreateData(billDto)) {
            Bill bill = buildBill(billDto);
            try {
                result = billDao.addBill(bill);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    private Bill buildBill(BillDto billDto) {
        Bill bill = new Bill();
        bill.setAmountDue(Integer.parseInt(billDto.getAmountDue()));
        bill.setInformation(billDto.getInformation());
        bill.setStatus(Bill.BillStatus.NOT_PAID);
        bill.setProjectId(Long.parseLong(billDto.getProjectId()));
        return bill;
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

    @Override
    public Bill updateBill(Bill bill) throws ServiceException {
        Bill updatedBill = null;
        BillDao billDao = daoProvider.getBillDao();
        try {
            updatedBill = billDao.updateBill(bill);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return updatedBill;
    }

    @Override
    public boolean removeBill(Long id) throws ServiceException {
        boolean deleted = false;
        BillDao billDao = daoProvider.getBillDao();
        try {
            deleted = billDao.removeBill(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return deleted;
    }

}
