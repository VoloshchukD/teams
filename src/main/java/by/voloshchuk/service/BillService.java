package by.voloshchuk.service;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.entity.dto.BillDto;
import by.voloshchuk.exception.ServiceException;

import java.util.List;

public interface BillService {

    /**
     * Bill adding logics.
     *
     * @param billDto - entity with required data for adding
     * @return boolean result of adding
     */
    boolean addBill(BillDto billDto) throws ServiceException;

    /**
     * Bill finding logics.
     *
     * @param id     - id of bill to find
     * @param userId - id of user owner of bills
     * @return {@link Bill}
     */
    Bill findBillByIdAndUserId(Long id, Long userId) throws ServiceException;

    /**
     * Users bill finding logics.
     *
     * @param userId - id of user for bill finding
     * @return list of {@link Bill}
     */
    List<Bill> findBillsByUserId(Long userId) throws ServiceException;

    /**
     * Projects bill finding logics.
     *
     * @param projectId - id of project with required bills
     * @return list of {@link Bill}
     */
    List<Bill> findBillsByProjectId(Long projectId) throws ServiceException;

    /**
     * Bill data updating logics.
     *
     * @param bill - data for bill updating
     * @return {@link Bill}
     */
    Bill updateBill(Bill bill) throws ServiceException;

    /**
     * Bill status updating logics.
     *
     * @param billId - bill id for update
     * @param status - status for update
     * @return updated status
     */
    String updateBillStatus(Long billId, String status) throws ServiceException;

    /**
     * Bill removing logics.
     *
     * @param id - bill id to delete
     * @return boolean removing result
     */
    boolean removeBill(Long id) throws ServiceException;

}
