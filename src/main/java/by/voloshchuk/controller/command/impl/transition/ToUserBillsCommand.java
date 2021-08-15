package by.voloshchuk.controller.command.impl.transition;

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
import java.util.List;

/**
 * Command to view all customer bills.
 *
 * @author Daniil Voloshchuk
 */
public class ToUserBillsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        List<Bill> bills = null;
        BillService billService = serviceProvider.getBillService();
        try {
            bills = billService.findBillsByUserId(userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        request.setAttribute(RequestParameter.BILLS, bills);
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD, CommandPath.BILLS_JSP);
        return router;
    }

}
