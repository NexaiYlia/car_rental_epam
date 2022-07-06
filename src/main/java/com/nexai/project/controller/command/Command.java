package com.nexai.project.controller.command;

import com.nexai.project.controller.Router;
import com.nexai.project.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    Router execute(HttpServletRequest request) throws CommandException;
}
