package by.voloshchuk.dao;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface BillDao {

    boolean addBill(Bill bill) throws DaoException;

    Bill findBillByIdAndUserId(Long id, Long userId) throws DaoException;

    List<Bill> findBillsByUserId(Long userId) throws DaoException;

    List<Bill> findBillsByProjectId(Long projectId) throws DaoException;

    Bill updateBill(Bill bill) throws DaoException;

    String updateBillStatus(Long billId, String status) throws DaoException;

    boolean removeBill(Long id) throws DaoException;

}
