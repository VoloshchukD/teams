package by.voloshchuk.dao;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public interface BillDao {

    /**
     * Save bill data to data source.
     *
     * @param bill - entity represents data to save
     * @return boolean result of saving
     */
    boolean addBill(Bill bill) throws DaoException;

    /**
     * Find bill that is assigned to user.
     *
     * @param id     - id of bill to find
     * @param userId - id of user owner of bill
     * @return {@link Bill}
     */
    Bill findBillByIdAndUserId(Long id, Long userId) throws DaoException;

    /**
     * Find all bills assigned to user at the storage.
     *
     * @param userId - id of user for bill finding
     * @return list of {@link Bill}
     */
    List<Bill> findBillsByUserId(Long userId) throws DaoException;

    /**
     * Find all billed on the project.
     *
     * @param projectId - id of project with required bills
     * @return list of {@link Bill}
     */
    List<Bill> findBillsByProjectId(Long projectId) throws DaoException;

    /**
     * Bill bring up to date.
     *
     * @param bill - data for bill updating
     * @return {@link Bill}
     */
    Bill updateBill(Bill bill) throws DaoException;

    /**
     * Status of bill to reset.
     *
     * @param billId - bill id for update
     * @param status - status for update
     * @return updated status
     */
    String updateBillStatus(Long billId, String status) throws DaoException;

    /**
     * Bill data deletion from storage.
     *
     * @param id - bill identifier to delete
     * @return boolean deletion result
     */
    boolean removeBill(Long id) throws DaoException;

}
