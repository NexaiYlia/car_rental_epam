package com.nexai.project.model.dao.impl;


import com.nexai.project.exception.DaoException;
import com.nexai.project.model.dao.PaymentDao;
import com.nexai.project.model.entity.Payment;
import com.nexai.project.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class PaymentDaoImpl implements PaymentDao {
    private static final String ADD_PAYMENT_QUERY = "INSERT INTO payment (order_id, status_id, total_price, payment_date)" +
            "VALUE (?, (SELECT id FROM status WHERE status_group='payment' AND status_name=?), ?, CURRENT_DATE);";

    @Override
    public Optional<Payment> getById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Payment> getAll() throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Payment entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PAYMENT_QUERY)) {
            statement.setInt(1, entity.getOrderId());
            statement.setString(2, entity.getStatus().toString().toLowerCase());
            statement.setBigDecimal(3, entity.getTotalPrice());

            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Payment> executeForManyResults(PreparedStatement statement) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Payment> executeForSingleResult(PreparedStatement statement) throws SQLException {
        throw new UnsupportedOperationException();
    }
}
