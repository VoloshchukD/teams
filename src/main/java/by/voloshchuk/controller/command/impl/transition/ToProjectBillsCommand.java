package by.voloshchuk.controller.command.impl.transition;

import by.voloshchuk.controller.command.*;
import by.voloshchuk.entity.Bill;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.util.RegexProperty;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Command to view project bills.
 *
 * @author Daniil Voloshchuk
 */
public class ToProjectBillsCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long projectId = Long.parseLong(request.getParameter(RequestParameter.PROJECT_ID));
        List<Bill> bills = null;
        BillService billService = serviceProvider.getBillService();
        try {
            bills = billService.findBillsByProjectId(projectId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        request.setAttribute(RequestParameter.BILLS, bills);
        request.setAttribute(CommandAttribute.BILL_AMOUNT_REGEX,
                RegexProperty.PROPERTY_BILL_AMOUNT_REGEX);
        request.setAttribute(CommandAttribute.BILL_INFORMATION_REGEX,
                RegexProperty.PROPERTY_BILL_INFORMATION_REGEX);

        CommandRouter router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                CommandPath.BILLS_JSP);
        return router;
    }

}
