package com.nexai.project.controller.command.impl;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.Router;
import com.nexai.project.model.entity.News;
import com.nexai.project.model.entity.Car;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Forwards to the editing pages for {@link News} or for {@link Car}
 * depends on {@link Command} given in request
 * @see HttpServletRequest
 */
public class GoToEditPage implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        return new Router(PagePath.NEWS_PAGE);
    }
}
