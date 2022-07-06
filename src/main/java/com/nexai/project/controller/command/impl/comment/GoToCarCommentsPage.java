package com.nexai.project.controller.command.impl.comment;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.CarComment;
import com.nexai.project.model.service.CarCommentService;
import com.nexai.project.model.service.CarService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.nexai.project.util.RequestUtil.processRequestErrors;

/**
 * Forwards to the page with car comments
 * @see CarComment
 * @see Command
 */
public class GoToCarCommentsPage implements Command {
    private static final Logger logger = LogManager.getLogger(GoToCarCommentsPage.class);


    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    private static final CarCommentService COMMENT_SERVICE = SERVICE_PROVIDER.getCommentService();
    private static final String REFERER = "referer";
    private static final String GO_TO_ORDERS_PAGE = "/controller?command=go_to_orders_page";
    private static final int RECORDS_PER_PAGE = 3;

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();

        int carId = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));

        CarService carService = SERVICE_PROVIDER.getCarService();
        try {
            /*
              If the request came from the orders page,
              then this ensures that the user can leave a comment.
             */
            if (request.getHeader(REFERER).contains(GO_TO_ORDERS_PAGE)) {
                request.setAttribute(ParameterName.ABLE_TO_COMMENT, true);
            }

            Car car = carService.getById(carId).get();
            request.setAttribute(ParameterName.CAR, car);

            processRequestErrors(request);
            processPage(carId, request);
            router.setPage(PagePath.COMMENTS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.FATAL, e);
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }


    private void processPage(int carId, HttpServletRequest request) throws ServiceException {
        int currentPage = Integer.parseInt(request.getParameter(ParameterName.CURRENT_PAGE));

        List<CarComment> comments = COMMENT_SERVICE.getCommentsForPage(carId, RECORDS_PER_PAGE, currentPage);
        request.setAttribute(ParameterName.DATA, comments);

        int records = COMMENT_SERVICE.getDataAmount(carId);

        // Calculates actual pages amount
        int pagesAmount = (int) Math.ceil(records / (float) RECORDS_PER_PAGE);

        request.setAttribute(ParameterName.PAGES_AMOUNT, pagesAmount);
        request.setAttribute(ParameterName.CURRENT_PAGE, currentPage);
    }
}