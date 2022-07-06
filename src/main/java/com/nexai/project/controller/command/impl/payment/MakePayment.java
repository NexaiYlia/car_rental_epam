package com.nexai.project.controller.command.impl.payment;


import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Payment;
import com.nexai.project.model.entity.type.PaymentStatus;
import com.nexai.project.model.service.PaymentService;
import com.nexai.project.model.service.ServiceProvider;
import com.nexai.project.util.DateUtil;

import com.nexai.project.validation.PaymentValidator;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * Create and saves {@link Payment} in the database
 *
 * @see Command
 */
public class MakePayment implements Command {
    private static final Logger logger = LogManager.getLogger(MakePayment.class);

    private static final DateUtil DATE_UTILS = new DateUtil();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        int orderId = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));

        try {
            String cardNumber = request.getParameter(ParameterName.CARD_NUMBER);
            String cvv = request.getParameter(ParameterName.CVV);
            LocalDate expiryDate = DATE_UTILS.parseDate(request.getParameter(ParameterName.EXPIRY_DATE));

            PaymentValidator validator = new PaymentValidator();
            if (!validator.isCardNumberValid(cardNumber)
                    || !validator.isCvvNumberValid(cvv)
                    || !validator.isExpirationDateValid(expiryDate)) {
                throw new InvalidDataException(validator.getMessage());
            }

            BigDecimal totalPrice = new BigDecimal(request.getParameter(ParameterName.TOTAL_PRICE));


            PaymentService paymentService = ServiceProvider.getInstance().getPaymentService();
            Payment payment = new Payment(orderId, PaymentStatus.APPROVED, totalPrice, null);
            paymentService.add(payment);
            logger.log(Level.DEBUG, "Payment added: ");
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.ERROR_500);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.PAYMENT_PAGE);
        }
        return router;
    }
}

