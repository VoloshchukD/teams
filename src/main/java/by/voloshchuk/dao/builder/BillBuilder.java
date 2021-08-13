package by.voloshchuk.dao.builder;

import by.voloshchuk.dao.impl.ConstantColumnName;
import by.voloshchuk.entity.Bill;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillBuilder implements Builder<Bill> {

    @Override
    public Bill buildEntity(ResultSet resultSet) throws SQLException {
        Bill bill = new Bill();
        bill.setId(Long.valueOf(resultSet.getString(ConstantColumnName.BILL_ID)));
        bill.setStatus(Bill.BillStatus.valueOf(
                resultSet.getString(ConstantColumnName.BILL_STATUS)));
        bill.setInformation(resultSet.getString(ConstantColumnName.BILL_INFORMATION));
        bill.setAmountDue(resultSet.getInt(ConstantColumnName.BILL_AMOUNT_DUE));
        bill.setProjectId(resultSet.getLong(ConstantColumnName.BILL_PROJECT_ID));
        return bill;
    }

}
