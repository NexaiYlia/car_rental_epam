package com.nexai.project.controller.command.impl;

import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;


/**
 * Change locale in the session and redirects to the referer from request
 *
 * @see Command
 */
public class ChangeLanguage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter(ParameterName.LOCALE);
        request.getSession().setAttribute(ParameterName.LOCALE, locale);
        return new Router((String) request.getSession().getAttribute(ParameterName.CURRENT_PAGE));
    }

}

