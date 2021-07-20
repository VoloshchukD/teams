package by.voloshchuk.dao.impl;

import by.voloshchuk.dao.BillDao;
import by.voloshchuk.dao.pool.CustomConnectionPool;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {

    private static final String SQL_ADD_BILL = "INSERT INTO teams.bills (status, information, amount_due, project_id) " +
            "VALUES (?, ?, ?, ?);";

    private static final String SQL_FIND_BILL_BY_ID = "SELECT * FROM teams.bills WHERE bill_id = ?";

    private static final String SQL_UPDATE_BILL = "UPDATE teams.bills SET status = ?, information = ?, amount_due = ? " +
            "WHERE bill_id = ?;";

    private static final String SQL_DELETE_BILL = "DELETE FROM teams.bills WHERE bill_id = ?;";

    private static final String SQL_FIND_BILLS_BY_USER_ID = "SELECT * FROM teams.bills INNER JOIN teams.projects " +
            "ON teams.projects.project_id=teams.bills.project_id INNER JOIN teams.technical_tasks " +
            "ON teams.projects.technical_task_id=teams.technical_tasks.technical_task_id " +
            "WHERE teams.technical_tasks.customer_id = ?";

    private CustomConnectionPool connectionPool = CustomConnectionPool.getInstance();

    public boolean addBill(Bill bill) throws DaoException {
        boolean isAdded = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_BILL)) {
            statement.setString(1, bill.getStatus());
            statement.setString(2, bill.getInformation());
            statement.setInt(3, bill.getAmountDue());
            statement.setLong(4, bill.getProjectId());
            isAdded = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isAdded;
    }

    public Bill findBillById(Long id) throws DaoException {
        Bill bill = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BILL_BY_ID)) {
            statement.setString(1, String.valueOf(id));
            ResultSet resultSet = statement.executeQuery();
            bill = new Bill();
            if (resultSet.next()) {
                bill.setId(Long.valueOf(resultSet.getString(ConstantColumnName.BILL_ID)));
                bill.setStatus(resultSet.getString(ConstantColumnName.BILL_STATUS));
                bill.setInformation(resultSet.getString(ConstantColumnName.BILL_INFORMATION));
                bill.setAmountDue(Integer.valueOf(resultSet.getString(ConstantColumnName.BILL_AMOUNT_DUE)));
                bill.setProjectId(Long.valueOf(resultSet.getString(ConstantColumnName.BILL_PROJECT_ID)));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return bill;
    }

    public List<Bill> findBillsByUserId(Long userId) throws DaoException {
        List<Bill> bills = new ArrayList<>();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_BILLS_BY_USER_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bill bill = new Bill();
                bill.setId(resultSet.getLong(ConstantColumnName.BILL_ID));
                bill.setStatus(resultSet.getString(ConstantColumnName.BILL_STATUS));
                bill.setInformation(resultSet.getString(ConstantColumnName.BILL_INFORMATION));
                bill.setAmountDue(resultSet.getInt(ConstantColumnName.BILL_AMOUNT_DUE));
                bill.setProjectId(resultSet.getLong(ConstantColumnName.BILL_PROJECT_ID));
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return bills;
    }

    public Bill updateBill(Bill bill) throws DaoException {
        Bill resultBill = null;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BILL)) {
            statement.setString(1, bill.getStatus());
            statement.setString(2, bill.getInformation());
            statement.setString(3, Integer.toString(bill.getAmountDue()));
            statement.setString(4, Long.toString(bill.getId()));
            int result = statement.executeUpdate();
            if (result == 1) {
                resultBill = bill;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return resultBill;
    }

    public boolean removeBill(Long id) throws DaoException {
        boolean isRemoved = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BILL)) {
            statement.setString(1, Long.toString(id));
            isRemoved = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return isRemoved;
    }

}
