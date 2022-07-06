package com.nexai.project.controller.command.impl.order;

import com.nexai.project.controller.AttributeName;
import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Order;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.entity.type.Role;
import com.nexai.project.model.service.OrderService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Forwards to the page with all orders if user's role is admin
 * and only orders that made by user itself if role is user
 *
 * @see Command
 * @see Role
 * @see Order
 */
public class GoToUserOrderPage implements Command {


    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(GoToUserOrderPage.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        List<Order> orders;
        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        User user = (User) request.getSession().getAttribute(AttributeName.USER);

        try {
            if (user != null) {
                router.setPage(PagePath.ORDER_FOR_USer_PAGE);
                orders = user.getRole() == Role.ADMIN
                        ? orderService.getAll()
                        : orderService.getAllByUserId(user.getId());
                request.setAttribute(ParameterName.ORDERS, orders);
            } else {
                router.setPage(PagePath.LOGIN_PAGE);
            }
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}