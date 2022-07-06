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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

/**
 * Edits car information and changes it in the database
 * @see Command
 * @see Car
 */

public class EditCar implements Command {

    private static final Logger logger = LogManager.getLogger(EditCar.class);
    private static final ServiceProvider serviceFactor = ServiceProvider.getInstance();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();

        int id = Integer.parseInt(request.getParameter(ParameterName.DATA_ID));
        String brand = request.getParameter(ParameterName.BRAND_EDITOR);
        String model = request.getParameter(ParameterName.MODEL_EDITOR);
        GearboxType gearbox = GearboxType.valueOf(request.getParameter(ParameterName.GEARBOX_EDITOR).toUpperCase());
        String year = request.getParameter(ParameterName.YEAR_EDITOR);
        EngineType engineType = EngineType.valueOf(request.getParameter(ParameterName.ENGINE_EDITOR).toUpperCase());
        CarClass carClass = CarClass.valueOf(request.getParameter(ParameterName.CLASS_EDITOR).toUpperCase());
        BigDecimal price = new BigDecimal(request.getParameter(ParameterName.PRICE_EDITOR));
        String imagePath = (String) request.getAttribute(ParameterName.IMAGE_PATH);

        try {
            CarService carService = serviceFactor.getCarService();
            Car car;
            car = new Car(id, brand, model, gearbox, year, engineType, price, carClass, imagePath);
            carService.update(car);
        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);

        } catch (InvalidDataException e) {
            logger.log(Level.ERROR, e);

        }
        router.setPage(PagePath.CARS_PAGE);
        return router;
    }
}




