package com.nexai.project.controller.command.impl.order;


import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Order;
import com.nexai.project.model.entity.type.OrderStatus;
import com.nexai.project.model.service.OrderService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Changes order status and
 * saves return comments and rejection comments in the database
 * @see Command
 * @see OrderStatus
 * @see Order
 */
public class ChangeOrderStatus implements Command {

    private static final Logger logger = LogManager.getLogger(ChangeOrderStatus.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();


    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        OrderStatus status = OrderStatus.valueOf(request.getParameter(ParameterName.STATUS).toUpperCase());
        int orderId = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));

        String rejectionComment = request.getParameter(ParameterName.REJECTION_COMMENT);
        String returnComment = request.getParameter(ParameterName.RETURN_COMMENT);

        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        try {
            orderService.changeStatus(orderId, status);
            if (rejectionComment != null) {
                orderService.addRejectionComment(orderId, rejectionComment);
            }
            if (returnComment != null) {
                orderService.addReturnComment(orderId, returnComment);
            }
        } catch (ServiceException e) {
            logger.log(Level.FATAL, e);

        }
        router.setPage(PagePath.ORDER_PAGE);
        return router;
    }
}

