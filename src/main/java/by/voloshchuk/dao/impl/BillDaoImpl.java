package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.BillDao;
import by.voloshchuk.dao.ConstantDaoQuery;
import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.builder.BillBuilder;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public class BillDaoImpl implements BillDao {

    private final DaoExecutor<Bill> executor;

    public BillDaoImpl() {
        Builder<Bill> builder = new BillBuilder();
        executor = new DaoExecutor<>(builder);
    }

    @Override
    public boolean addBill(Bill bill) throws DaoException {
        Object[] parameters = {bill.getStatus().toString(), bill.getInformation(),
                bill.getAmountDue(), bill.getProjectId()};
        boolean added = executor.executeUpdate(ConstantDaoQuery.ADD_BILL_QUERY, parameters);
        return added;
    }

    @Override
    public Bill findBillByIdAndUserId(Long id, Long userId) throws DaoException {
        Object[] parameters = {id, userId};
        Bill bill = executor.executeQuery(
                ConstantDaoQuery.FIND_BILL_BY_ID_AND_USER_ID_QUERY, parameters);
        return bill;
    }

    @Override
    public List<Bill> findBillsByUserId(Long userId) throws DaoException {
        Object[] parameters = {userId};
        List<Bill> bills = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_BILLS_BY_USER_ID_QUERY, parameters);
        return bills;
    }

    @Override
    public List<Bill> findBillsByProjectId(Long projectId) throws DaoException {
        Object[] parameters = {projectId};
        List<Bill> bills = executor.executeQueryMultipleResult(
                ConstantDaoQuery.FIND_BILLS_BY_PROJECT_ID_QUERY, parameters);
        return bills;
    }

    @Override
    public Bill updateBill(Bill bill) throws DaoException {
        Bill resultBill = null;
        Object[] parameters = {bill.getInformation(), bill.getAmountDue(), bill.getId()};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_BILL_QUERY, parameters);
        if (result) {
            resultBill = bill;
        }
        return resultBill;
    }

    @Override
    public String updateBillStatus(Long billId, String status) throws DaoException {
        String resultStatus = null;
        Object[] parameters = {status, billId};
        boolean result = executor.executeUpdate(
                ConstantDaoQuery.UPDATE_BILL_STATUS_QUERY, parameters);
        if (result) {
            resultStatus = status;
        }
        return resultStatus;
    }

    @Override
    public boolean removeBill(Long id) throws DaoException {
        Object[] parameters = {id};
        boolean removed = executor.executeUpdate(
                ConstantDaoQuery.DELETE_BILL_QUERY, parameters);
        return removed;
    }

}
