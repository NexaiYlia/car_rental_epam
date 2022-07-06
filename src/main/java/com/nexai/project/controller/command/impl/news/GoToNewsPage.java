package com.nexai.project.controller.command.impl.news;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.News;
import com.nexai.project.model.service.NewsService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * Forwards to the page with all news
 *
 * @see News
 * @see Command
 */
public class GoToNewsPage implements Command {
    private static final Logger logger = LogManager.getLogger(GoToNewsPage.class);

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            NewsService newsService = SERVICE_PROVIDER.getNewsService();
            List<News> news = newsService.getAll();
            request.setAttribute(ParameterName.NEWS, news);
            router.setPage(PagePath.NEWS_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            router.setPage(PagePath.ERROR_500);
        }
        return router;
    }
}


