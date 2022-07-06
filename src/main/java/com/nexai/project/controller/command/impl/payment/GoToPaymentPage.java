package com.nexai.project.controller.command.impl.payment;


import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Order;
import com.nexai.project.model.entity.Payment;
import com.nexai.project.model.service.OrderService;
import com.nexai.project.model.service.ServiceProvider;
import com.nexai.project.util.PriceCalculator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

import static com.nexai.project.util.RequestUtil.processRequestErrors;

/**
 * Forwards to the payment page
 * @see Payment
 * @see Command
 */

public class GoToPaymentPage implements Command {


    private static final Logger logger = LogManager.getLogger(GoToPaymentPage.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String page;
        Router router = new Router();

        int orderId = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));
        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        PriceCalculator priceCalculator = new PriceCalculator();

        processRequestErrors(request);
        try {
            Order order = orderService.getById(orderId).get();
            BigDecimal totalPrice = priceCalculator.calculatePrice(order);
            request.setAttribute(ParameterName.TOTAL_PRICE, totalPrice);
            request.setAttribute(ParameterName.DATA, order);
            page = PagePath.PAYMENT_PAGE;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            page = PagePath.ERROR_500;
        }
        router.setPage(page);
        return router;
    }
}




