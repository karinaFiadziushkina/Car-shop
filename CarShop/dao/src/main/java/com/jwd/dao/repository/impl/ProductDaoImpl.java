package com.jwd.dao.repository.impl;

import com.jwd.dao.connection.ConnectionPool;
import com.jwd.dao.entity.Car;
import com.jwd.dao.entity.Pageable;
import com.jwd.dao.exception.DaoException;
import com.jwd.dao.repository.ProductDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

public class ProductDaoImpl extends AbstractDao implements ProductDao {
  private static final String COUNT_ALL_FILTERED_SORTED = "SELECT count(c.id) FROM cars c";
  private static final String FIND_PAGE_FILTERED_SORTED = "SELECT * FROM cars c ORDER BY c.%s %s LIMIT ? OFFSET ?";
  private static final String DELETE_CAR = "DELETE FROM cars WHERE id = ?";
  private static final String SAVE_CAR = "INSERT INTO cars VALUES (DEFAULT, ?, ?, ?, ?)";
  private static final String UPDATE_PRODUCT = "UPDATE cars SET brand_id = ?, model = ?, price = ?, quantity = ? WHERE id = ?";
  private static final String FIND_CAR_BY_ID = "SELECT * FROM books WHERE id = ?";

  public ProductDaoImpl(final ConnectionPool connectionPool) {
    super(connectionPool);
  }

  @Override
  public Pageable<Car> findPageByFilter(Pageable<Car> daoProductPageable) throws DaoException {
    final int offset = (daoProductPageable.getPageNumber() - 1) * daoProductPageable.getLimit();
    List<Object> parameters1 = Collections.emptyList(); // todo implement filtering
    List<Object> parameters2 = Arrays.asList( // todo implement filtering
        daoProductPageable.getLimit(),
        offset
    );
    Connection connection = null;
    PreparedStatement preparedStatement1 = null;
    PreparedStatement preparedStatement2 = null;
    ResultSet resultSet1 = null;
    ResultSet resultSet2 = null;
    try {
      connection = getConnection(false);
      preparedStatement1 = getPreparedStatement(COUNT_ALL_FILTERED_SORTED, connection, parameters1);
      final String findPageOrderedQuery =
          String.format(FIND_PAGE_FILTERED_SORTED, daoProductPageable.getSortBy(), daoProductPageable.getDirection());
      preparedStatement2 = getPreparedStatement(findPageOrderedQuery, connection, parameters2);
      resultSet1 = preparedStatement1.executeQuery();
      resultSet2 = preparedStatement2.executeQuery();
      connection.commit();

      return getCarPageable(daoProductPageable, resultSet1, resultSet2);
    } catch (SQLException | DaoException e) {
      e.printStackTrace();
      throw new DaoException(e);
    } finally {
      close(resultSet1, resultSet2);
      close(preparedStatement1, preparedStatement2);
      retrieve(connection);
    }
  }

  @Override
  public boolean delete(Long id) throws DaoException {
    boolean result;
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = getConnection(false);
      statement = connection.prepareStatement(DELETE_CAR);
      statement.setLong(1, id);
      result = statement.executeUpdate() != 0;
      connection.commit();
      return result;
    } catch (Exception e) {
      try {
        if (nonNull(connection)) {
          connection.rollback();
        }
      } catch (SQLException ex) {
        throw new DaoException(ex);
      }
      throw new DaoException(e);
    } finally {
      closeStatement(statement);
    }
  }

  @Override
  public boolean save(Car car) throws DaoException {
    boolean result;
    List<Object> parameters = Arrays.asList(
        car.getBrand_id(),
        car.getModel(),
        car.getPrice(),
        car.getQuantity()
    );
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = getConnection(false);
      statement = getPreparedStatement(SAVE_CAR, connection, parameters);
      result = statement.executeUpdate() != 0;
      connection.commit();
      return result;

    } catch (SQLException e) {
      try {
        if (nonNull(connection)) {
          connection.rollback();
        }
      } catch (SQLException ex) {
        throw new DaoException(ex);
      }
      throw new DaoException(e);
    } finally {
      closeStatement(statement);
    }
  }

  @Override
  public boolean update(Car car) throws DaoException {
    boolean result = false;
    List<Object> parameters = Arrays.asList(
        car.getBrand_id(),
        car.getModel(),
        car.getPrice(),
        car.getQuantity()
    );
    Connection connection = null;
    PreparedStatement updateStatement = null;
    Car existingCar;
    try {
      connection = getConnection(false);
      existingCar = findById(car.getId());
      if (nonNull(existingCar)) {
        updateStatement = getPreparedStatement(UPDATE_PRODUCT, connection, parameters);
        result = updateStatement.executeUpdate() != 0;
      }
      connection.commit();
      return result;

    } catch (Exception e) {
      try {
        if (nonNull(connection)) {
          connection.rollback();
        }
      } catch (SQLException ex) {
        throw new DaoException(ex);
      }
      throw new DaoException(e);
    } finally {
      closeStatement(updateStatement);
    }
  }

  @Override
  public Car findById(Long id) throws DaoException {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Car car = null;
    try {
      connection = getConnection(false);
      statement = connection.prepareStatement(FIND_CAR_BY_ID);
      statement.setLong(1, id);
      resultSet = statement.executeQuery();
      if (resultSet.next()) {
        car = getCarFormResultSet(resultSet);
      }
      connection.commit();
      return car;
    } catch (Exception e) {
      throw new DaoException(e);
    } finally {
      close(resultSet);
      closeStatement(statement);
    }
  }

  private Car getCarFormResultSet(ResultSet resultSet) throws SQLException {

    long id = resultSet.getLong(1);
    long brand_id = resultSet.getLong(2);
    String model = resultSet.getString(3);
    long price = resultSet.getLong(4);
    long quantity = resultSet.getLong(5);

    return new Car(id, brand_id, model, price, quantity);
  }

  private Pageable<Car> getCarPageable(Pageable<Car> daoProductPageable,
                                       ResultSet totalProducts,
                                       ResultSet productsPageList) throws SQLException {
    final Pageable<Car> pageable = new Pageable<>();
    long totalElements = 0L;
    while (totalProducts.next()) {
      totalElements = totalProducts.getLong(1);
    }
    final List<Car> cars = new ArrayList<>();
    while (productsPageList.next()) {
      long id = productsPageList.getLong(1);
      long brand_id = productsPageList.getLong(2);
      String model = productsPageList.getString(3);
      long price = productsPageList.getLong(4);
      long quantity = productsPageList.getLong(5);
      cars.add(new Car(id, brand_id, model, price, quantity));
    }
    pageable.setPageNumber(daoProductPageable.getPageNumber());
    pageable.setLimit(daoProductPageable.getLimit());
    pageable.setTotalElements(totalElements);
    pageable.setElements(cars);
    pageable.setFilter(daoProductPageable.getFilter());
    pageable.setSortBy(daoProductPageable.getSortBy());
    pageable.setDirection(daoProductPageable.getDirection());
    return pageable;
  }
}
