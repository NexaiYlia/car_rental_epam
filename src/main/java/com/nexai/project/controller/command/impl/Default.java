package com.nexai.project.controller.command.impl;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class Default implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.INDEX, Router.Type.REDIRECT);
    }
}
