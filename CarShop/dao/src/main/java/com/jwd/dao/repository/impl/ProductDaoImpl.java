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

import static com.jwd.dao.util.Util.convertNullToEmpty;

public class ProductDaoImpl extends AbstractDao implements ProductDao {
  private static final String COUNT_ALL_FILTERED_SORTED = "SELECT count(c.id) FROM cars c;";
  private static final String FIND_PAGE_FILTERED_SORTED = "SELECT * FROM cars c ORDER BY c.%s %s LIMIT ? OFFSET ?;";

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
