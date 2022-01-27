package com.jwd.dao.repository.impl;

import com.jwd.dao.connection.ConnectionPool;
import com.jwd.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static java.util.Objects.nonNull;

public abstract class AbstractDao {
  private final ConnectionPool connectionPool;

  public AbstractDao(final ConnectionPool connectionPool) {
    this.connectionPool = connectionPool;
  }

  protected Connection getConnection(final boolean hasAutocommit) throws DaoException {
    final Connection connection = connectionPool.take();
    try {
      connection.setAutoCommit(hasAutocommit);
      return connection;
    } catch (SQLException e) {
      connectionPool.retrieve(connection);
      throw new DaoException(e);
    }
  }

  protected void retrieve(final Connection connection) {
    connectionPool.retrieve(connection);
  }

  protected PreparedStatement getPreparedStatement(final String query, final Connection connection,
                                                   final List<Object> parameters) throws SQLException {
    final PreparedStatement preparedStatement = connection.prepareStatement(query);
    setPreparedStatementParameters(preparedStatement, parameters);
    return preparedStatement;
  }

  protected void setPreparedStatementParameters(final PreparedStatement preparedStatement,
                                                final List<Object> parameters) throws SQLException {
    for (int i = 0, queryParameterIndex = 1; i < parameters.size(); i++, queryParameterIndex++) {
      final Object parameter = parameters.get(i);
      setPreparedStatementParameter(preparedStatement, queryParameterIndex, parameter);
    }
  }

  protected void setPreparedStatementParameter(final PreparedStatement preparedStatement,
                                               final int queryParameterIndex, final Object parameter) throws SQLException {
    if (Long.class == parameter.getClass()) {
      preparedStatement.setLong(queryParameterIndex, (Long) parameter);
    } else if (Integer.class == parameter.getClass()) {
      preparedStatement.setInt(queryParameterIndex, (Integer) parameter);
    } else if (String.class == parameter.getClass()) {
      preparedStatement.setString(queryParameterIndex, (String) parameter);
    }
  }

  protected void close(final ResultSet... resultSets) {
    if (nonNull(resultSets)) {
      for (final ResultSet resultSet : resultSets) {
        if (nonNull(resultSet)) {
          try {
            resultSet.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  protected void close(final PreparedStatement... preparedStatements) {
    if (nonNull(preparedStatements)) {
      for (final PreparedStatement preparedStatement : preparedStatements) {
        if (nonNull(preparedStatement)) {
          try {
            preparedStatement.close();
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  protected int prepareOffset(final int pageNumber, final int limit) {
    return (pageNumber - 1) * limit;
  }

  protected String setSortAndDirection(final String getPageQuery, final String sortBy, final String direction) {
    return String.format(getPageQuery, sortBy, direction);
  }

  protected void processAbnormalCase(boolean isTrue, String message) throws DaoException {
    if (isTrue) {
      throw new DaoException(message);
    }
  }
}
