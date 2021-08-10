package by.voloshchuk.controller.command.impl;

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

public class MakePaymentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Long billId = Long.parseLong(request.getParameter(RequestParameter.BILL_ID));
        BillService billService = serviceProvider.getBillService();
        try {
            billService.updateBillStatus(billId, Bill.BillStatus.PAID.toString());
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_BILLS);
        return router;
    }

}
