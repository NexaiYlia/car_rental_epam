package com.nexai.project.controller.command.impl.car;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Deletes from database
 * @see Command
 * @see Car
 */

public class DeleteCar implements Command {

    private static final Logger logger = LogManager.getLogger(DeleteCar.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router(PagePath.CARS_PAGE, Router.Type.REDIRECT);
        try {
            int id = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));
            SERVICE_PROVIDER.getCarService().deleteById(id);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
        }
        return router;
    }
}

