package com.nexai.project.controller.command.impl.order;


import com.nexai.project.controller.AttributeName;
import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.Order;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.entity.type.OrderStatus;
import com.nexai.project.model.service.CarService;
import com.nexai.project.model.service.OrderService;
import com.nexai.project.model.service.ServiceProvider;
import com.nexai.project.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

/**
 * Create and saves {@link Order} in the database
 * @see Command
 */
public class MakeOrder implements Command {
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(MakeOrder.class);
    private static final DateUtil DATE_UTILS = new DateUtil();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int carId = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));
        OrderService orderService = SERVICE_PROVIDER.getOrderService();
        CarService carService = SERVICE_PROVIDER.getCarService();
        User user = ((User) request.getSession().getAttribute(AttributeName.USER));

        try {
            LocalDate startDate = DATE_UTILS.parseDate(request.getParameter(ParameterName.START_DATE));
            LocalDate endDate = DATE_UTILS.parseDate(request.getParameter(ParameterName.END_DATE));
            Car car = carService.getById(carId).get();
            Order order = new Order(user, car, OrderStatus.NEW, startDate, endDate, "", "");
            orderService.add(order);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.ORDER_PAGE);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.ORDER_PAGE);
        }
        return router;
    }
}

