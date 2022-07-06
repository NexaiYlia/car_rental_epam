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
import com.nexai.project.model.entity.UserPassportData;
import com.nexai.project.model.service.ServiceProvider;
import com.nexai.project.model.service.UserService;
import com.nexai.project.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * Creates {@link User} and saves it in the database and in the session
 * @see Command
 */
public class Register implements Command {

    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    private static final DateUtil DATE_UTILS = new DateUtil();
    private static final Logger logger = LogManager.getLogger(Register.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        String page;
        Router router = new Router();


        String email = request.getParameter(ParameterName.EMAIL);
        String password = request.getParameter(ParameterName.PASSWORD);

        String firstName = request.getParameter(ParameterName.FIRST_NAME);
        String secondName = request.getParameter(ParameterName.SECOND_NAME);
        String middleName = request.getParameter(ParameterName.MIDDLE_NAME);
        String phoneNumber = request.getParameter(ParameterName.PHONE_NUMBER);

        String passportNumber = request.getParameter(ParameterName.PASSPORT_NUMBER);
        String identificationNumber = request.getParameter(ParameterName.IDENTIFICATION_NUMBER);
        String issueDateParameter = request.getParameter(ParameterName.ISSUE_DATE);

        HttpSession session = request.getSession();
        UserService userService = SERVICE_PROVIDER.getUserService();

        try {
            LocalDate issueDate = DATE_UTILS.parseDate(issueDateParameter);
            UserPassportData passport = new UserPassportData(passportNumber, identificationNumber, issueDate);
            userService.registerUser(email, password, passport);
            User user = userService.findUserByEmailAndPassword(email, password).get();
            session.setAttribute(AttributeName.USER, user);
            page = PagePath.NEWS_PAGE;
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            page = PagePath.REGISTER_PAGE;

        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            page = PagePath.REGISTER_PAGE;
        }
        router.setPage(page);
        return router;
    }
}


