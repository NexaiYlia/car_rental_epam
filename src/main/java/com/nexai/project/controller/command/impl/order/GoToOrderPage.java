package com.nexai.project.controller.command.impl.order;

import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Forwards to order page where user choose the dates of order
 * @see Command
 * @see com.nexai.project.model.entity.Order
 */

public class GoToOrderPage implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(GoToOrderPage.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();

        return router;
    }
}



