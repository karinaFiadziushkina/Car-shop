package com.jwd.service.logic.impl;

import com.jwd.dao.DaoFactory;
import com.jwd.dao.entity.Car;
import com.jwd.dao.entity.Pageable;
import com.jwd.dao.exception.DaoException;
import com.jwd.dao.repository.ProductDao;
import com.jwd.service.entity.Page;
import com.jwd.service.entity.Product;
import com.jwd.service.exception.ServiceException;
import com.jwd.service.logic.ProductService;
import com.jwd.service.validator.ServiceValidator;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class ProductServiceImpl implements ProductService {

  private final ProductDao productDao = DaoFactory.getInstance().getProductDao();
  private final ServiceValidator validator = new ServiceValidator();

  @Override
  public Page<Product> showProducts(Page<Product> productPageRequest) throws ServiceException {
    try {
      // validation
      validator.validate(productPageRequest);

      // prepare data
      final Pageable<Car> daoProductPageable = convertToPageableProduct(productPageRequest);
      // process data
      final Pageable<Car> carsPageable = productDao.findPageByFilter(daoProductPageable);

      // return
      return convertToServicePage(carsPageable);
    } catch (final DaoException e) {
      throw new ServiceException(e);
    }
  }

  private Page<Product> convertToServicePage(Pageable<Car> carsPageable) {
    Page<Product> page = new Page<>();
    page.setPageNumber(carsPageable.getPageNumber());
    page.setLimit(carsPageable.getLimit());
    page.setTotalElements(carsPageable.getTotalElements());
    page.setElements(convertToProducts(carsPageable.getElements()));
    page.setFilter(convertToProduct(carsPageable.getFilter()));
    page.setSortBy(carsPageable.getSortBy());
    page.setDirection(carsPageable.getDirection());
    return page;
  }

  private List<Product> convertToProducts(final List<Car> cars) {
    List<Product> list = new ArrayList<>();
    for (Car car : cars) {
      list.add(convertToProduct(car));
    }
    return list;
  }

  private Product convertToProduct(final Car car) {
    Product product = null;
    if (nonNull(car)) {
      product = new Product();
      product.setId(car.getId());
      product.setBrand_id(car.getBrand_id());
      product.setModel(car.getModel());
      product.setPrice(car.getPrice());
      product.setQuantity(car.getQuantity());
    }
    return product;
  }

  private Pageable<Car> convertToPageableProduct(Page<Product> pageableRequest) {
    final Pageable<Car> pageable = new Pageable<>();
    pageable.setPageNumber(pageableRequest.getPageNumber());
    pageable.setLimit(pageableRequest.getLimit());
    pageable.setTotalElements(pageableRequest.getTotalElements());
    pageable.setFilter(convertToCar(pageableRequest.getFilter()));
    pageable.setSortBy(pageableRequest.getSortBy());
    pageable.setDirection(pageableRequest.getDirection());
    return pageable;
  }

  private Car convertToCar(final Product product) {
    Car car = null;
    if (nonNull(product)) {
      car = new Car();
      car.setId(product.getId());
      car.setBrand_id(product.getBrand_id());
      car.setModel(product.getModel());
      car.setPrice(product.getPrice());
      car.setQuantity(product.getQuantity());
    }
    return car;
  }
}
