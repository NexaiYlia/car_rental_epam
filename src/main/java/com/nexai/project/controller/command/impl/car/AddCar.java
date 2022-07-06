package com.nexai.project.controller.command.impl.car;

import com.nexai.project.controller.PagePath;
import com.nexai.project.controller.ParameterName;
import com.nexai.project.controller.Router;
import com.nexai.project.controller.command.Command;
import com.nexai.project.exception.CommandException;
import com.nexai.project.exception.InvalidDataException;
import com.nexai.project.exception.ServiceException;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.type.CarClass;
import com.nexai.project.model.entity.type.EngineType;
import com.nexai.project.model.entity.type.GearboxType;
import com.nexai.project.model.service.CarService;
import com.nexai.project.model.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * Adds car to the database
 * @see Command
 * @see Car
 */
public class AddCar implements Command {

    private static final Logger logger = LogManager.getLogger(AddCar.class);
    private static final ServiceProvider SERVICE_PROVIDER = ServiceProvider.getInstance();
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession();

        String brand = request.getParameter(ParameterName.BRAND_EDITOR);
        String model = request.getParameter(ParameterName.MODEL_EDITOR);
        GearboxType gearbox = GearboxType.valueOf(request.getParameter(ParameterName.GEARBOX_EDITOR).toUpperCase());
        String year = request.getParameter(ParameterName.YEAR_EDITOR);
        EngineType engine = EngineType.valueOf(request.getParameter(ParameterName.ENGINE_EDITOR).toUpperCase());
        BigDecimal price = new BigDecimal(request.getParameter(ParameterName.PRICE_EDITOR));
        CarClass carClass = CarClass.valueOf(request.getParameter(ParameterName.CLASS_EDITOR).toUpperCase());
        String imagePath = (String) request.getAttribute(ParameterName.IMAGE_PATH);


        Router router = new Router();

        try {
            CarService carService = SERVICE_PROVIDER.getCarService();
            Car car = new Car(brand, model, gearbox, year, engine, price, carClass, imagePath);
            carService.add(car);
            router = new Router(PagePath.CARS_PAGE);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.CAR_EDIT_PAGE);
        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);
            router.setPage(PagePath.CAR_EDIT_PAGE);
        }
        return router;
    }
}


