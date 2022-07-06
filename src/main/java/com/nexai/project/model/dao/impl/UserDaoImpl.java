package com.nexai.project.model.dao.impl;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.dao.UserDao;
import com.nexai.project.model.entity.type.Role;
import com.nexai.project.model.entity.User;
import com.nexai.project.model.entity.UserPassportData;
import com.nexai.project.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final String GET_ALL_QUERY = "SELECT users.id, email, user_password, role.role FROM users" +
            " INNER JOIN role on users.role = role.id;";
    private static final String LOGIN_QUERY = "SELECT users.id, users.email, users.user_password, role.role FROM users" +
            " INNER JOIN role on users.role = role.id WHERE email=? AND user_password=?;";
    private static final String GET_BY_ID_QUERY = "SELECT users.id, email, user_password, role.role FROM users" +
            " INNER JOIN role on users.role = role.id WHERE users.id=?;";
    private static final String REGISTER_QUERY = "INSERT INTO users (email, user_password, role) VALUE (?, ?, ?);";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM users WHERE id=?";
    private static final String ADD_PASSPORT_QUERY = "INSERT INTO passport " +
            "(passport_number, identification_number, issue_date) " +
            "VALUE (?, ?, ?);";
    private static final String ADD_DETAILS_QUERY = "INSERT INTO user_details (" +
            "user_id, passport_number, phone_number, first_name, second_name, middle_name) " +
            "VALUE (?, ?, ?, ?, ?, ?);";

    private static final int DEFAULT_ROLE_ID = 1;


    @Override
    public Optional<User> getById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            statement.setInt(1, id);

            return executeForSingleResult(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> getAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)) {
            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void add(User entity) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(int id) throws DaoException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> executeForManyResults(PreparedStatement statement) throws SQLException {
        List<User> users = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String email = resultSet.getString(2);
            String password = resultSet.getString(3);
            Role role = Role.valueOf(resultSet.getString(4).toUpperCase());

            users.add(new User(id, email, password, role));
        }
        return users;
    }

    @Override
    public Optional<User> executeForSingleResult(PreparedStatement statement) throws SQLException {
        Optional<User> user = Optional.empty();

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            String email = resultSet.getString(2);
            String password = resultSet.getString(3);
            Role role = Role.valueOf(resultSet.getString(4).toUpperCase());

            user = Optional.of(new User(id, email, password, role));
        }
        return user;
    }

    @Override
    public void registerUser(String email, String password, UserPassportData passport) throws DaoException {
        registerUser(email, password, passport, DEFAULT_ROLE_ID);
    }

    @Override
    public void registerUser(String email, String password, UserPassportData passport, int role) throws DaoException {
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(REGISTER_QUERY, Statement.RETURN_GENERATED_KEYS);


            connection.setAutoCommit(false);

            statement.setString(1, email);
            statement.setString(2, password);
            statement.setInt(3, role);
            statement.executeUpdate();

            logger.info("User added successfully");

            ResultSet generatedKeys = statement.getGeneratedKeys();

            statement = connection.prepareStatement(ADD_PASSPORT_QUERY);
            statement.setString(1, passport.getPassportNumber());
            statement.setString(2, passport.getIdentificationNumber());
            statement.setDate(3, Date.valueOf(passport.getIssueDate()));
            statement.execute();

            logger.info("Passport added successfully");

            connection.commit();
            statement.close();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwable) {
                throw new DaoException(throwable);
            }
            throw new DaoException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException sqlException) {
                throw new DaoException(sqlException);
            }
        }
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(LOGIN_QUERY)) {
            statement.setString(1, email);
            statement.setString(2, password);

            return executeForSingleResult(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}