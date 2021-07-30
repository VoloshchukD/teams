package by.voloshchuk.service;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface BillService {

    List<Bill> findBillsByUserId(Long userId) throws ServiceException;

}
