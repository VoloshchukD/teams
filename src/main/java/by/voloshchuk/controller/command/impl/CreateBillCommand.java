package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandAttribute;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.entity.dto.BillDto;
import by.voloshchuk.exception.ServiceException;
import by.voloshchuk.service.BillService;
import by.voloshchuk.service.ServiceProvider;
import by.voloshchuk.util.RequestParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to create bill for project.
 *
 * @author Daniil Voloshchuk
 */
public class CreateBillCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        BillDto billDto = RequestParser.buildBillDto(request);
        BillService billService = serviceProvider.getBillService();
        boolean created = false;
        try {
            created = billService.addBill(billDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router;
        if (created) {
            router = new CommandRouter(CommandRouter.RouterType.REDIRECT,
                    CommandPath.TO_PROJECT_BILLS + billDto.getProjectId());
        } else {
            router = new CommandRouter(CommandRouter.RouterType.FORWARD,
                    CommandPath.BILL_CREATION);
            request.setAttribute(CommandAttribute.ERROR, true);
        }
        return router;
    }

}
