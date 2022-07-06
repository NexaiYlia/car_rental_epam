package com.nexai.project.controller.command.impl;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Removes user from the session and redirects to the news page
 *
 * @see Command
 */
public class SignOut implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        request.getSession().invalidate();
        return new Router(PagePath.NEWS_PAGE);
    }
}

