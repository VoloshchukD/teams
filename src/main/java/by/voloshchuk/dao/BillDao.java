package by.voloshchuk.dao;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface BillDao {

    boolean addBill(Bill bill) throws DaoException;

    Bill findBillById(Long id) throws DaoException;

    List<Bill> findBillsByUserId(Long userId) throws DaoException;

    Bill updateBill(Bill bill) throws DaoException;

    boolean removeBill(Long id) throws DaoException;

}
