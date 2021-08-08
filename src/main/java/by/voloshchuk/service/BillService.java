package by.voloshchuk.service;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface BillService {

    Bill findBillById(Long id) throws ServiceException;

    boolean addBill(Bill bill) throws ServiceException;

    String updateBillStatus(Long billId, String status) throws ServiceException;

    List<Bill> findBillsByUserId(Long userId) throws ServiceException;

    List<Bill> findBillsByProjectId(Long projectId) throws ServiceException;

    Bill updateBill(Bill bill) throws ServiceException;

    boolean removeBill(Long id) throws ServiceException;

}
