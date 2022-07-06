package com.nexai.project.controller.command.impl.comment;

import com.nexai.project.controller.AttributeName;
import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.CarComment;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.service.CarCommentService;
import com.nexai.project.model.service.CarService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Adds user's car comment in the database
 * @see Command
 * @see CarComment
 */

public class LeaveComment implements Command {

    private static final Logger logger = LogManager.getLogger(LeaveComment.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();

        String content = request.getParameter(ParameterName.CONTENT_EDITOR);
        int carId = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));

        CarService carService = SERVICE_PROVIDER.getCarService();
        CarCommentService commentService = SERVICE_PROVIDER.getCommentService();
        try {
            Car car = carService.getById(carId).get();
            User user = (User) request.getSession().getAttribute(AttributeName.USER);
            CarComment comment = new CarComment(user, car, content);
            commentService.add(comment);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.COMMENTS_PAGE);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.COMMENTS_PAGE);
        }
        return router;
    }
}
