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

/**
 * Command to move to payment form for definite bill.
 *
 * @author Daniil Voloshchuk
 */
public class ToPaymentFormCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        Long billId = Long.parseLong(request.getParameter(RequestParameter.BILL_ID));
        Long userId = (Long) request.getSession().getAttribute(CommandAttribute.USER_ID);
        Bill bill = null;
        BillService billService = serviceProvider.getBillService();
        try {
            bill = billService.findBillByIdAndUserId(billId, userId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }

        CommandRouter router;
        if (bill != null) {
            request.setAttribute(RequestParameter.BILL, bill);

            request.setAttribute(CommandAttribute.HOLDER_REGEX,
                    RegexProperty.PROPERTY_PAYMENT_HOLDER_REGEX);
            request.setAttribute(CommandAttribute.MM_REGEX,
                    RegexProperty.PROPERTY_PAYMENT_MM_REGEX);
            request.setAttribute(CommandAttribute.YY_REGEX,
                    RegexProperty.PROPERTY_PAYMENT_YY_REGEX);
            request.setAttribute(CommandAttribute.NUMBER_REGEX,
                    RegexProperty.PROPERTY_PAYMENT_NUMBER_REGEX);
            request.setAttribute(CommandAttribute.CVC_REGEX,
                    RegexProperty.PROPERTY_PAYMENT_CVC_REGEX);


            router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                    CommandPath.PAYMENT_FORM_JSP);
        } else {
            router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                    CommandPath.ERROR_500_JSP);
        }

        return router;
    }

}
