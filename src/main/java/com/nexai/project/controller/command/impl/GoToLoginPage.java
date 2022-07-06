package com.nexai.project.controller.command.impl;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static com.nexai.project.util.RequestUtil.processRequestErrors;

/**
 * Forwards to the login page
 * @see Command
 */
public class GoToLoginPage implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        processRequestErrors(request);

        return new Router(PagePath.LOGIN_PAGE);
    }
}


