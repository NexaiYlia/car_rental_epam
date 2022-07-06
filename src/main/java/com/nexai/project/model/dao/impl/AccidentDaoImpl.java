package com.nexai.project.model.dao.impl;


import com.nexai.project.exception.DaoException;
import com.nexai.project.model.dao.AccidentDao;
import com.nexai.project.model.dao.DaoHelper;
import com.nexai.project.model.dao.OrderDao;
import com.nexai.project.model.entity.Accident;
import com.nexai.project.model.entity.Order;
import com.nexai.project.model.pool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccidentDaoImpl implements AccidentDao {

    private static final String GET_ALL_QUERY = "SELECT * FROM accidents;";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM accidents WHERE id=?;";

    private static final String UPDATE_QUERY = "UPDATE accidents SET date=?, description=?, cost=?, order_id=? WHERE id=?;";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM accidents WHERE id=?;";
    private static final String ADD_QUERY = "INSERT INTO accidents (date, description, cost, order_id) " +
            "VALUE (?, ?, ?, ?)";
    private static final String GET_BY_ORDER_QUERY = "SELECT * FROM accidents WHERE order_id=?;";

    private static final OrderDao ORDER_DAO = DaoHelper.getInstance().getOrderDao();

    @Override
    public List<Accident> getAllByOrderId(Integer orderId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ORDER_QUERY)) {
            statement.setInt(1, orderId);
            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<Accident> getById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setInt(1, id);
            return executeForSingleResult(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Accident> getAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)) {
            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void add(Accident entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_QUERY)) {
            statement.setDate(1, Date.valueOf(entity.getDate()));
            statement.setString(2, entity.getDescription());
            statement.setBigDecimal(3, entity.getCost());
            statement.setInt(4, entity.getOrder().getId());

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
    public List<Accident> executeForManyResults(PreparedStatement statement) throws SQLException, DaoException {
        List<Accident> accidents = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            LocalDate date = LocalDate.parse(resultSet.getString(2));
            String description = resultSet.getString(3);
            BigDecimal cost = resultSet.getBigDecimal(4);
            Integer orderId = resultSet.getInt(5);
            Order order = ORDER_DAO.getById(orderId).get();
            Accident accident = new Accident(id, date, description, cost, order);

            accidents.add(accident);
        }

        return accidents;
    }

    @Override
    public Optional<Accident> executeForSingleResult(PreparedStatement statement) throws SQLException, DaoException {
        Optional<Accident> accident = Optional.empty();

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            LocalDate date = LocalDate.parse(resultSet.getString(2));
            String description = resultSet.getString(3);
            BigDecimal cost = resultSet.getBigDecimal(4);
            Integer orderId = resultSet.getInt(5);
            Order order = ORDER_DAO.getById(orderId).get();

            accident = Optional.of(new Accident(id, date, description, cost, order));
        }

        return accident;
    }
}

