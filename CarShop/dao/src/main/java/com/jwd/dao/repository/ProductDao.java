package com.jwd.dao.repository;

import com.jwd.dao.entity.Pageable;
import com.jwd.dao.entity.Car;
import com.jwd.dao.exception.DaoException;

public interface ProductDao {
  Pageable<Car> findPageByFilter(Pageable<Car> daoProductPageable) throws DaoException;

  boolean delete(Long id) throws DaoException;

  boolean save(Car car) throws DaoException;

  boolean update(Car car) throws DaoException;

  Car findById(Long id) throws DaoException;
}
