package com.nexai.project.model.dao.impl;


import com.nexai.project.exception.DaoException;
import com.nexai.project.model.dao.CarDao;
import com.nexai.project.model.entity.Car;
import com.nexai.project.model.entity.type.*;
import com.nexai.project.model.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDaoImpl implements CarDao {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM cars WHERE id=?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM cars;";
    private static final String GET_BY_NAME = "SELECT * FROM cars WHERE CONCAT(brand, ' ', model) LIKE CONCAT('%', ?, '%')";
    private static final String GET_BY_YEAR = "SELECT * FROM cars WHERE manufactured_year=?;";
    private static final String GET_BY_CLASS = "SELECT * FROM cars WHERE class=?;";
    private static final String UPDATE_CAR_QUERY = "UPDATE cars SET brand=?, model=?, gearbox=?, manufactured_year=?, engine_type=?, class=?, price_per_day=?, image_path=? WHERE id=?;";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM cars WHERE id=?;";
    private static final String ADD_CAR_QUERY = "INSERT INTO cars (brand, model, gearbox, manufactured_year, engine_type, price_per_day, class, image_path) " +
            "VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_BRAND_QUERY = "SELECT * FROM cars WHERE brand=?;";
    private static final String FIND_BY_PRICE_QUERY = "SELECT * FROM cars WHERE  price_per_day BETWEEN ? AND ?;";
    private static final String FIND_BY_CAR_CLASS_QUERY = "SELECT * FROM cars WHERE class=?;";

    @Override
    public Optional<Car> getById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, id);
            return executeForSingleResult(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> getAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)) {
            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void add(Car entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_CAR_QUERY)) {
            statement.setString(1, entity.getBrand());
            statement.setString(2, entity.getModel());
            statement.setString(3, entity.getGearbox().toString().toLowerCase());
            statement.setString(4, entity.getManufacturedYear());
            statement.setString(5, entity.getEngineType().toString().toLowerCase());
            statement.setBigDecimal(6, entity.getPricePerDay());
            statement.setString(7, entity.getCarClass().toString().toLowerCase());
            statement.setString(8, entity.getImagePath());

            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> getCarsByName(String criteria) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_NAME)) {
            statement.setString(1, criteria);
            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> getCarsByYear(String year) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_YEAR)) {
            statement.setString(1, year.toLowerCase());
            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> getCarsByClass(CarClass carClass) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_CLASS)) {
            statement.setString(1, carClass.toString().toLowerCase());
            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Car entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CAR_QUERY)) {
            statement.setString(1, entity.getBrand());
            statement.setString(2, entity.getModel());
            statement.setString(3, entity.getGearbox().toString().toLowerCase());
            statement.setString(4, entity.getManufacturedYear());
            statement.setString(5, entity.getEngineType().toString().toLowerCase());
            statement.setString(6, entity.getCarClass().toString().toLowerCase());
            statement.setBigDecimal(7, entity.getPricePerDay());
            statement.setString(8, entity.getImagePath());
            statement.setInt(9, entity.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> executeForManyResults(PreparedStatement statement) throws SQLException {
        List<Car> cars = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String brand = resultSet.getString(2);
            String model = resultSet.getString(3);
            GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(4).toUpperCase());
            String year = resultSet.getString(5);
            EngineType engineType = EngineType.valueOf(resultSet.getString(6).toUpperCase());
            BigDecimal pricePerDay = resultSet.getBigDecimal(7);
            CarClass carClass = CarClass.valueOf(resultSet.getString(8).toUpperCase());
            String imagePath = resultSet.getString(9);

            Car car = new Car(id, brand, model, gearboxType, year,
                    engineType, pricePerDay, carClass, imagePath);

            cars.add(car);
        }

        return cars;
    }

    @Override
    public Optional<Car> executeForSingleResult(PreparedStatement statement) throws SQLException {
        Optional<Car> car = Optional.empty();

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String brand = resultSet.getString(2);
            String model = resultSet.getString(3);
            GearboxType gearboxType = GearboxType.valueOf(resultSet.getString(4).toUpperCase());
            String year = resultSet.getString(5);
            EngineType engineType = EngineType.valueOf(resultSet.getString(6).toUpperCase());
            BigDecimal pricePerDay = resultSet.getBigDecimal(7);
            CarClass carClass = CarClass.valueOf(resultSet.getString(8).toUpperCase());
            String imagePath = resultSet.getString(9);

            car = Optional.of(new Car(id, brand, model, gearboxType, year,
                    engineType, pricePerDay, carClass, imagePath));
        }

        return car;
    }


    @Override
    public List<Car> findCarsByPricePerDayBetween(BigDecimal pricePerDayAfter, BigDecimal pricePerDayBefore) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_PRICE_QUERY)) {
            statement.setInt(1, Integer.valueOf(String.valueOf(pricePerDayAfter)));
            statement.setInt(2, Integer.valueOf(String.valueOf(pricePerDayBefore)));

            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> findCarsByCarType(CarClass carClass) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_CAR_CLASS_QUERY)) {
            statement.setString(1, String.valueOf(carClass));
            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Car> findCarsByBrand(String brand) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_BRAND_QUERY)) {
            statement.setString(1, brand);

            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
