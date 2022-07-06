package com.nexai.project.model.dao.impl;


import com.nexai.project.exception.DaoException;
import com.nexai.project.model.dao.UserPassportDataDao;
import com.nexai.project.model.entity.UserPassportData;
import com.nexai.project.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class UserPassportDataDaoImpl implements UserPassportDataDao {
    private static final String GET_BY_ID_QUERY = "SELECT * FROM passport WHERE passport_number=?;";
    private static final String ADD_PASSPORT_QUERY = "INSERT INTO passport " +
            "(passport_number, identification_number, issue_date) " +
            "VALUE (?, ?, ?);";

    @Override
    public void add(UserPassportData entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_PASSPORT_QUERY)) {
            statement.setString(1, entity.getPassportNumber());
            statement.setString(2, entity.getIdentificationNumber());

            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<UserPassportData> getByPassportNumber(String passportNumber) throws DaoException {
        Optional<UserPassportData> passport = Optional.empty();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {
            statement.setString(1, passportNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String identificationNumber = resultSet.getString(2);
                LocalDate issueDate = LocalDate.parse(resultSet.getString(3));
                passport = Optional.of(new UserPassportData(passportNumber, identificationNumber, issueDate));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return passport;
    }
}
