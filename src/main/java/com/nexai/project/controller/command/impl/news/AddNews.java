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

import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Adds {@link News} to the database
 * @see Command
 */
public class AddNews implements Command {
    private static final Logger logger = LogManager.getLogger(AddNews.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        HttpSession session = request.getSession();
        int userId = ((User) session.getAttribute(AttributeName.USER)).getId();
        String header = request.getParameter(ParameterName.HEADER_EDITOR);
        String content = request.getParameter(ParameterName.CONTENT_EDITOR);
        String imagePath = (String) request.getAttribute(ParameterName.IMAGE_PATH);

        try {
            NewsService newsService = SERVICE_PROVIDER.getNewsService();
            News news = new News(userId, header, content, imagePath);
            newsService.add(news);
            router.setPage(PagePath.NEWS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.NEWS_EDIT_PAGE);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.NEWS_EDIT_PAGE);
        }
        return router;
    }
}


