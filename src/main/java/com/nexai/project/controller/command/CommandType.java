package com.nexai.project.controller.command;

import com.nexai.project.controller.command.impl.*;
import com.nexai.project.controller.command.impl.car.AddCar;
import com.nexai.project.controller.command.impl.car.DeleteCar;
import com.nexai.project.controller.command.impl.car.EditCar;
import com.nexai.project.controller.command.impl.car.GoToCarPage;
import com.nexai.project.controller.command.impl.comment.DeleteComment;
import com.nexai.project.controller.command.impl.comment.GoToCarCommentsPage;
import com.nexai.project.controller.command.impl.comment.LeaveComment;
import com.nexai.project.controller.command.impl.news.*;
import com.nexai.project.controller.command.impl.order.ChangeOrderStatus;
import com.nexai.project.controller.command.impl.order.GoToOrderPage;
import com.nexai.project.controller.command.impl.order.GoToUserOrderPage;
import com.nexai.project.controller.command.impl.order.MakeOrder;
import com.nexai.project.controller.command.impl.payment.GoToPaymentPage;
import com.nexai.project.controller.command.impl.payment.MakePayment;

public enum CommandType {

    LOGIN(new Login()),
    ADD_NEWS(new AddNews()),
    EDIT_NEWS(new EditNews()),
    DELETE_NEWS(new DeleteNews()),
    GO_TO_LOGIN_PAGE(new GoToLoginPage()),
    GO_TO_REGISTER_PAGE(new GoToRegisterPage()),
    GO_TO_NEWS_PAGE(new GoToNewsPage()),
    GO_TO_CAR_COMMENTS_PAGE(new GoToCarCommentsPage()),
    LEAVE_COMMENT(new LeaveComment()),
    DELETE_COMMENT(new DeleteComment()),
    REGISTER(new Register()),
    SIGN_OUT(new SignOut()),
    GO_TO_CARS_PAGE(new GoToCarPage()),
    CHANGE_LANG(new ChangeLanguage()),
    GO_TO_CAR_EDIT_PAGE(new GoToEditPage()),
    ADD_CAR(new AddCar()),
    EDIT_CAR(new EditCar()),
    DELETE_CAR(new DeleteCar()),
    GO_TO_ORDER_PAGE(new GoToOrderPage()),
    GO_TO_ORDERS_PAGE(new GoToUserOrderPage()),
    MAKE_ORDER(new MakeOrder()),
    CHANGE_ORDER_STATUS(new ChangeOrderStatus()),
    GO_TO_PAYMENT_PAGE(new GoToPaymentPage()),
    MAKE_PAYMENT(new MakePayment()),
    DEFAULT(new Default());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static Command commandOf(String commandStr) {
        String commandName = commandStr.toUpperCase();
        CommandType[] values = CommandType.values();
        for (CommandType value : values) {
            if (value.name().equals(commandName)) {
                CommandType commandType = CommandType.valueOf(commandName);
                return commandType.getCommand();
            }
        }
        return DEFAULT.getCommand();
    }
}
