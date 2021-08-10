package by.voloshchuk.controller.command.impl;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateBillCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Bill bill = createBill(request);
        BillService billService = serviceProvider.getBillService();
        try {
            billService.addBill(bill);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_CREATE_REQUIREMENT);
        return router;
    }

    private Bill createBill(HttpServletRequest request) {
        Bill bill = new Bill();
        bill.setAmountDue(Integer.parseInt(request.getParameter("amount")));
        bill.setInformation(request.getParameter("information"));
        bill.setStatus(Bill.BillStatus.NOT_PAID);
        bill.setProjectId(Long.parseLong(request.getParameter("project-id")));
        return bill;
    }

}
