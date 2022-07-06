package com.nexai.project.model.dao.impl;

import com.nexai.project.exception.DaoException;
import com.nexai.project.model.dao.NewsDao;
import com.nexai.project.model.entity.News;
import com.nexai.project.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsDaoImpl implements NewsDao {
    private static final Logger logger = LogManager.getLogger(NewsDaoImpl.class);

    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String GET_BY_ID_QUERY = "SELECT * FROM news WHERE id=?;";
    private static final String GET_ALL_QUERY = "SELECT * FROM news ORDER BY publication_date DESC";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM news WHERE id=?;";
    private static final String UPDATE_NEWS_QUERY = "UPDATE news SET header=?, content=?, image_path=? WHERE id=?";
    private static final String ADD_NEWS_QUERY = "INSERT INTO news (user_id, header, content, publication_date, image_path) " +
            "VALUE (?, ?, ?, CURRENT_DATE, ?)";

    @Override
    public Optional<News> getById(int id) throws DaoException {
        try (Connection connection = pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID_QUERY)) {

            statement.setInt(1, id);
            return executeForSingleResult(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<News> getAll() throws DaoException {
        try (Connection connection = pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_ALL_QUERY)) {

            return executeForManyResults(statement);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void add(News entity) throws DaoException {
        try (Connection connection = pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_NEWS_QUERY)) {

            statement.setInt(1, entity.getUserId());
            statement.setString(2, entity.getHeader());
            statement.setString(3, entity.getContent());
            statement.setString(4, entity.getImagePath());

            statement.execute();
            logger.info("News  was added successfully");
        } catch (SQLException e) {
            logger.error("News  wasn't added");
            throw new DaoException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DaoException {
        try (Connection connection = pool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setInt(1, id);

            statement.execute();
            logger.info("News  was deleted successfully");
        } catch (SQLException e) {
            logger.error("News  wasn't deleted ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(News entity) throws DaoException {
        try (Connection connection = pool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_NEWS_QUERY)) {
            statement.setString(1, entity.getHeader());
            statement.setString(2, entity.getContent());
            statement.setString(3, entity.getImagePath());
            statement.setInt(4, entity.getId());

            statement.execute();
            logger.info("News  was updated successfully");
        } catch (SQLException e) {
            logger.error("News  wasn't updated ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public List<News> executeForManyResults(PreparedStatement statement) throws SQLException {
        List<News> news = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            Integer userId = resultSet.getInt(2);
            String header = resultSet.getString(3);
            String content = resultSet.getString(4);
            LocalDate date = LocalDate.parse(resultSet.getString(5));
            String imagePath = resultSet.getString(6);

            news.add(new News(id, userId, header, content, date, imagePath));
        }
        return news;
    }

    @Override
    public Optional<News> executeForSingleResult(PreparedStatement statement) throws SQLException {
        Optional<News> news = Optional.empty();

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt(1);
            Integer userId = resultSet.getInt(2);
            String header = resultSet.getString(3);
            String content = resultSet.getString(4);
            LocalDate date = LocalDate.parse(resultSet.getString(5));
            String imagePath = resultSet.getString(6);

            news = Optional.of(new News(id, userId, header, content, date, imagePath));
        }
        return news;
    }
}
