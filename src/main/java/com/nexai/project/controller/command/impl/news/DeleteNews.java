package com.nexai.project.controller.command.impl.news;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.service.NewsService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Deletes News from the database
 * @see Command
 */
public class DeleteNews implements Command {
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final Logger logger = LogManager.getLogger(DeleteNews.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int id = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));

        try {
            NewsService newsService = SERVICE_PROVIDER.getNewsService();
            newsService.deleteById(id);
            router.setPage(PagePath.NEWS_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return router;
    }
}


