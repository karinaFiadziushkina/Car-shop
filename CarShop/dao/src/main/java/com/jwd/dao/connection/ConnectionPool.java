package com.jwd.dao.connection;

import com.jwd.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
  Connection take() throws DaoException;
  void retrieve(final Connection connection);
}
