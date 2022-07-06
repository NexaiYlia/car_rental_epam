package com.nexai.project.controller.command.impl.news;

import com.nexai.project.controller.AttributeName;
import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.News;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.service.NewsService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Edits {@link News} information and update it in the database
 * @see Command
 */
public class EditNews implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(EditNews.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int id = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));
        int userId = ((User) request.getSession().getAttribute(AttributeName.USER)).getId();
        String header = request.getParameter(ParameterName.HEADER_EDITOR);
        String content = request.getParameter(ParameterName.CONTENT_EDITOR);
        String imagePath = (String) request.getAttribute(ParameterName.IMAGE_PATH);

        try {
            NewsService newsService = SERVICE_PROVIDER.getNewsService();
            newsService.update(new News(id, userId, header, content, imagePath));
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
           router.setPage(PagePath.NEWS_PAGE);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.NEWS_EDIT_PAGE);
        }
        return router;
    }
}


