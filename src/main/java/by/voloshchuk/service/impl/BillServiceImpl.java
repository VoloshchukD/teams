package by.voloshchuk.service.impl;

import by.voloshchuk.dao.BillDao;
import by.voloshchuk.dao.DaoProvider;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.entity.dto.BillDto;
import by.voloshchuk.entity.dto.PaymentDto;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import by.voloshchuk.service.validator.PaymentValidator;
import by.voloshchuk.service.validator.Validator;
import by.voloshchuk.service.validator.ValidatorProvider;
import by.voloshchuk.util.DtoEntityConverter;

import java.util.List;

public class BillServiceImpl implements BillService {

    private static DaoProvider daoProvider = DaoProvider.getInstance();

    @Override
    public Bill findBillByIdAndUserId(Long id, Long userId) throws ServiceException {
        Bill bill = null;
        BillDao billDao = daoProvider.getBillDao();
        try {
            bill = billDao.findBillByIdAndUserId(id, userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception while find bill ", e);
        }
        return bill;
    }

    @Override
    public boolean addBill(BillDto billDto) throws ServiceException {
        boolean result = false;
        BillDao billDao = daoProvider.getBillDao();
        Validator<BillDto> billValidator = ValidatorProvider.getInstance().getBillValidator();
        if (billValidator.validateCreateData(billDto)) {
            Bill bill = DtoEntityConverter.buildBill(billDto);
            try {
                result = billDao.addBill(bill);
            } catch (DaoException e) {
                throw new ServiceException("Exception while add bill ", e);
            }
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
            throw new ServiceException("Exception while find bill ", e);
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
            throw new ServiceException("Exception while find bill ", e);
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
            throw new ServiceException("Exception while update bill status ", e);
        }
        return resultStatus;
    }

    @Override
    public String payForBill(PaymentDto paymentDto) throws ServiceException {
        String resultStatus = null;
        PaymentValidator<PaymentDto> paymentValidator =
                ValidatorProvider.getInstance().getPaymentValidator();
        if (paymentValidator.validatePayment(paymentDto)) {
            resultStatus = updateBillStatus(paymentDto.getBillId(),
                    Bill.BillStatus.PAID.toString());
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
            throw new ServiceException("Exception while update bill ", e);
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
            throw new ServiceException("Exception while remove bill ", e);
        }
        return deleted;
    }

}
