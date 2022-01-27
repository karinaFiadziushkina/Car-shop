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
import java.util.List;

import static com.jwd.dao.util.Util.convertNullToEmpty;

public class ProductDaoImpl extends AbstractDao implements ProductDao {

  private static final String COUNT_ALL_FILTERED_SORTED =
      "SELECT count(c.id) FROM cars c " +
          "WHERE UPPER(c.brand_id) LIKE CONCAT('%', UPPER(?), '%') " +
          "AND UPPER(c.model) LIKE CONCAT('%', UPPER(?), '%') " +
          "AND UPPER(c.price) LIKE CONCAT('%', UPPER(?), '%') " +
          "AND UPPER(c.quantity) LIKE CONCAT('%', UPPER(?), '%');";
  //    private static final String COUNT_ALL_FILTERED_SORTED = "SELECT count(p.id) FROM products p WHERE p.colm LIKE CONCAT('%', ?, '%')";";
  //private static final String FIND_PAGE_FILTERED_SORTED = "SELECT * FROM products p ORDER BY p.name ASC LIMIT 5 OFFSET 0";
  private static final String FIND_PAGE_FILTERED_SORTED =
      "SELECT * FROM cars c " +
          "WHERE UPPER(c.brand_id) LIKE CONCAT('%%', UPPER(?), '%%') " +
          "AND UPPER(c.model) LIKE CONCAT('%%', UPPER(?), '%%') " +
          "AND UPPER(c.price) LIKE CONCAT('%%', UPPER(?), '%%') " +
          "AND UPPER(c.quantity) LIKE CONCAT('%%', UPPER(?), '%%')" +
          "ORDER BY c.%s %s LIMIT ? OFFSET ?;";

  //private static final String FIND_PAGE_FILTERED_SORTED = "SELECT * FROM products p WHERE p.colm LIKE CONCAT('%', ?, '%')" ORDER BY p.name ASC LIMIT 5 OFFSET 0";
  public ProductDaoImpl(final ConnectionPool connectionPool) {
    super(connectionPool);
  }

  @Override
  public Pageable<Car> findPageByFilter(Pageable<Car> daoProductPageable) throws DaoException {
    final List<Object> parameters1 = Arrays.asList(
        convertNullToEmpty(String.valueOf(daoProductPageable.getFilter().getBrand_id())),
        convertNullToEmpty(daoProductPageable.getFilter().getModel()).toUpperCase(),
        convertNullToEmpty(String.valueOf(daoProductPageable.getFilter().getPrice())),
        convertNullToEmpty(String.valueOf(daoProductPageable.getFilter().getQuantity()))
    );
    final List<Object> parameters2 = Arrays.asList(
        convertNullToEmpty(String.valueOf(daoProductPageable.getFilter().getBrand_id())),
        convertNullToEmpty(daoProductPageable.getFilter().getModel()).toUpperCase(),
        convertNullToEmpty(String.valueOf(daoProductPageable.getFilter().getPrice())),
        convertNullToEmpty(String.valueOf(daoProductPageable.getFilter().getQuantity())),
        daoProductPageable.getLimit(),
        prepareOffset(daoProductPageable.getPageNumber(), daoProductPageable.getLimit())
    );
    final String getPageQuery = setSortAndDirection(FIND_PAGE_FILTERED_SORTED, daoProductPageable.getSortBy(), daoProductPageable.getDirection());
    final Connection connection = getConnection(false);
    try (final PreparedStatement preparedStatementCountAllProducts =
             getPreparedStatement(COUNT_ALL_FILTERED_SORTED, connection, parameters1);
         final PreparedStatement preparedStatementGetProductsPageList =
             getPreparedStatement(getPageQuery, connection, parameters2);
         final ResultSet totalProducts = preparedStatementCountAllProducts.executeQuery();
         final ResultSet productsPageList = preparedStatementGetProductsPageList.executeQuery();) {
      connection.commit();
      return getCarPageable(daoProductPageable, totalProducts, productsPageList);
    } catch (SQLException e) {
      throw new DaoException(e);
    } finally {
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
