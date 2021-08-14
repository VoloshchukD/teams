package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.controller.command.RequestParameter;
import by.voloshchuk.entity.dto.PaymentDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import by.voloshchuk.service.ServiceProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to make bill payment.
 *
 * @author Daniil Voloshchuk
 */
public class MakePaymentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        PaymentDto paymentDto = buildDto(request);
        BillService billService = serviceProvider.getBillService();
        try {
            billService.payForBill(paymentDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_BILLS);
        return router;
    }

    private PaymentDto buildDto(HttpServletRequest request) {
        PaymentDto paymentDto = new PaymentDto();
        Long billId = Long.parseLong(request.getParameter(RequestParameter.BILL_ID));
        paymentDto.setBillId(billId);
        paymentDto.setCardHolder(request.getParameter(RequestParameter.CARD_HOLDER));
        paymentDto.setMonth(request.getParameter(RequestParameter.CARD_MONTH));
        paymentDto.setYear(request.getParameter(RequestParameter.CARD_YEAR));
        paymentDto.setNumber(request.getParameter(RequestParameter.CARD_NUMBER));
        paymentDto.setCvc(request.getParameter(RequestParameter.CARD_CVC));
        return paymentDto;
    }

}
