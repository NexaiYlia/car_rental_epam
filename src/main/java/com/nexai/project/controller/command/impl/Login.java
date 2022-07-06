package com.nexai.project.controller.command.impl;

import com.nexai.project.controller.AttributeName;
import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.service.ServiceProvider;
import com.nexai.project.model.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;


/**
 * Take {@link User} from database and adds him to the session
 * and redirects to the referer from the request
 * @see Command
 */
public class Login implements Command {
    private static final Logger logger = LogManager.getLogger(Login.class);

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(ParameterName.EMAIL);
        String password = request.getParameter(ParameterName.PASSWORD);
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();

        HttpSession session = request.getSession();

        String page = PagePath.LOGIN_PAGE;
        Router router = new Router();

        Optional<User> user;

        try {
            user = userService.findUserByEmailAndPassword(email, password);

            if (!user.isPresent()) {
                throw new InvalidDataException();
            }
            session.setAttribute(AttributeName.USER, user.get());
        } catch (ServiceException e) {
            logger.error(Level.FATAL, e);
            router.setPage(PagePath.LOGIN_PAGE);

        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.LOGIN_PAGE);

        }
        return new Router(page);
    }
}
