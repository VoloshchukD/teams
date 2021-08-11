package by.voloshchuk.controller.command.impl;

import by.voloshchuk.controller.command.Command;
import by.voloshchuk.controller.command.CommandPath;
import by.voloshchuk.controller.command.CommandRouter;
import by.voloshchuk.entity.dto.BillDto;
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
 * Command to create bill for project.
 *
 * @author Daniil Voloshchuk
 */
public class CreateBillCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private static ServiceProvider serviceProvider = ServiceProvider.getInstance();

    @Override
    public CommandRouter execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        BillDto billDto = createBill(request);
        BillService billService = serviceProvider.getBillService();
        try {
            billService.addBill(billDto);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e.getMessage());
        }
        CommandRouter router = new CommandRouter(CommandRouter.RouterType.REDIRECT, CommandPath.TO_CREATE_REQUIREMENT);
        return router;
    }

    private BillDto createBill(HttpServletRequest request) {
        BillDto billDto = new BillDto();
        billDto.setAmountDue(request.getParameter("amount"));
        billDto.setInformation(request.getParameter("information"));
        billDto.setProjectId(request.getParameter("project-id"));
        return billDto;
    }

}
