package com.nexai.project.controller.command.impl.comment;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.service.CarCommentService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Deletes CarComment from database
 * @see Command
 */
public class DeleteComment implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteComment.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();

        int commentId = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));

        CarCommentService commentService = SERVICE_PROVIDER.getCommentService();
        try {
            commentService.deleteById(commentId);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);

        }
        router.setPage(PagePath.COMMENTS_PAGE);
        return router;
    }
}
