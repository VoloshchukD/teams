package by.voloshchuk.controller.command.impl.async;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.controller.command.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptPaymentCommand implements AsyncCommand {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Long billId = Long.parseLong(request.getParameter(RequestParameter.BILL_ID));
        BillService billService = serviceProvider.getBillService();
        try {
            billService.updateBillStatus(billId, Bill.BillStatus.ACCEPTED.toString());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

    }

}
