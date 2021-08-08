package by.voloshchuk.servlet.command.impl;

import by.voloshchuk.entity.Bill;
import by.voloshchuk.entity.Project;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import by.voloshchuk.service.ProjectService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.servlet.command.Command;
import by.voloshchuk.servlet.command.CommandPath;
import by.voloshchuk.servlet.command.CommandRouter;
import by.voloshchuk.servlet.command.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBillCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        BillService projectService = serviceProvider.getBillService();
        Bill bill = createBill(request);
        try {
            projectService.updateBill(bill);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                CommandPath.PROJECT_BILLS + request.getParameter(RequestParameter.PROJECT_ID));
        return router;
    }

    private Bill createBill(HttpServletRequest request) {
        Bill bill = new Bill();
        Long billId = Long.parseLong(request.getParameter(RequestParameter.BILL_ID));
        bill.setId(billId);
        bill.setAmountDue(Integer.parseInt(request.getParameter(RequestParameter.BILL_AMOUNT)));
        bill.setInformation(request.getParameter(RequestParameter.BILL_INFORMATION));
        return bill;
    }

}
