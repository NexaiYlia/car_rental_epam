package com.nexai.project.controller.command.impl.car;


import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.service.CarService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Forwards to the page with cars
 *
 * @see Command
 * @see Car
 */
public class GoToCarPage implements Command {

    private static final Logger logger = LogManager.getLogger(GoToCarPage.class);

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router = new Router();

        try {
            CarService carService = SERVICE_PROVIDER.getCarService();

            String criteria = request.getParameter(ParameterName.SEARCH);
            List<Car> cars;
            if (criteria != null && !criteria.isEmpty()) {
                cars = SERVICE_PROVIDER.getCarService().getCarsByName(criteria);
            } else {
                cars = carService.getAll();
            }
            request.setAttribute(ParameterName.CARS, cars);
            router.setPage(PagePath.CARS_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            router.setPage(PagePath.ERROR_404);
        }
        return router;
    }
}





