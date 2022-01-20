package com.jwd.dao.repository;

import com.jwd.dao.entity.Pageable;
import com.jwd.dao.entity.Car;
import com.jwd.dao.exception.DaoException;

public interface ProductDao {
  Pageable<Car> findPageByFilter(Pageable<Car> daoProductPageable) throws DaoException;
}
