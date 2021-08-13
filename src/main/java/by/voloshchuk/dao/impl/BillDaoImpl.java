package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.BillDao;
import by.voloshchuk.dao.DaoExecutor;
import by.voloshchuk.dao.builder.BillBuilder;
import by.voloshchuk.dao.builder.Builder;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;

import java.util.List;

public class BillDaoImpl implements BillDao {

    private static final String ADD_BILL_QUERY = "INSERT INTO bills " +
            "(status, information, amount_due, project_id) " +
            "VALUES (?, ?, ?, ?);";

    private static final String FIND_BILL_BY_ID_AND_USER_ID_QUERY = "SELECT * FROM bills " +
            "INNER JOIN user_project_maps " +
            "ON bills.project_id = user_project_maps.project_id " +
            "WHERE bills.bill_id = ? AND user_project_maps.user_id = ?";

    private static final String FIND_BILLS_BY_PROJECT_ID_QUERY = "SELECT * FROM bills " +
            "INNER JOIN projects " +
            "ON projects.project_id = bills.project_id " +
            "WHERE projects.project_id = ?";

    private static final String FIND_BILLS_BY_USER_ID_QUERY = "SELECT * FROM bills " +
            "INNER JOIN projects " +
            "ON projects.project_id = bills.project_id INNER JOIN technical_tasks " +
            "ON projects.technical_task_id = technical_tasks.technical_task_id " +
            "WHERE technical_tasks.customer_id = ?";

    private static final String UPDATE_BILL_QUERY = "UPDATE bills " +
            "SET information = ?, amount_due = ? " +
            "WHERE bill_id = ?;";

    private static final String UPDATE_BILL_STATUS_QUERY = "UPDATE bills " +
            "SET status = ? WHERE bill_id = ?;";

    private static final String DELETE_BILL_QUERY = "DELETE FROM bills WHERE bill_id = ?;";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    private final DaoExecutor<Bill> executor;

    public BillDaoImpl() {
        Builder<Bill> builder = new BillBuilder();
        executor = new DaoExecutor<>(builder);
    }

    public boolean addBill(Bill bill) throws DaoException {
        Object[] parameters = {bill.getStatus().toString(), bill.getInformation(),
                bill.getAmountDue(), bill.getProjectId()};
        boolean added = executor.executeUpdate(ADD_BILL_QUERY, parameters);
        return added;
    }

    public Bill findBillByIdAndUserId(Long id, Long userId) throws DaoException {
        Object[] parameters = {id, userId};
        Bill bill = executor.executeQuery(FIND_BILL_BY_ID_AND_USER_ID_QUERY, parameters);
        return bill;
    }

    public List<Bill> findBillsByUserId(Long userId) throws DaoException {
        Object[] parameters = {userId};
        List<Bill> bills = executor.executeQueryMultipleResult(
                FIND_BILLS_BY_USER_ID_QUERY, parameters);
        return bills;
    }

    public List<Bill> findBillsByProjectId(Long projectId) throws DaoException {
        Object[] parameters = {projectId};
        List<Bill> bills = executor.executeQueryMultipleResult(
                FIND_BILLS_BY_PROJECT_ID_QUERY, parameters);
        return bills;
    }

    public Bill updateBill(Bill bill) throws DaoException {
        Bill resultBill = null;
        Object[] parameters = {bill.getInformation(), bill.getAmountDue(), bill.getId()};
        boolean result = executor.executeUpdate(UPDATE_BILL_QUERY, parameters);
        if (result) {
            resultBill = bill;
        }
        return resultBill;
    }

    public String updateBillStatus(Long billId, String status) throws DaoException {
        String resultStatus = null;
        Object[] parameters = {status, billId};
        boolean result = executor.executeUpdate(UPDATE_BILL_STATUS_QUERY, parameters);
        if (result) {
            resultStatus = status;
        }
        return resultStatus;
    }

    public boolean removeBill(Long id) throws DaoException {
        Object[] parameters = {id};
        boolean removed = executor.executeUpdate(DELETE_BILL_QUERY, parameters);
        return removed;
    }

}
