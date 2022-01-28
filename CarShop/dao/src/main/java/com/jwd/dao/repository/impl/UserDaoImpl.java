package com.jwd.dao.repository.impl;

import com.jwd.dao.configuration.DataBaseConfig;
import com.jwd.dao.connection.ConnectionPool;
import com.jwd.dao.connection.impl.ConnectionPoolImpl;
import com.jwd.dao.entity.UserRow;
import com.jwd.dao.entity.UserRowDto;
import com.jwd.dao.exception.DaoException;
import com.jwd.dao.repository.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jwd.dao.util.Util.convertNullToEmpty;
import static java.util.Objects.isNull;

public class UserDaoImpl extends AbstractDao implements UserDao {

  private static final String FIND_ALL_USERS_QUERY = "SELECT u.id, u.login, u.firstname, u.lastname FROM users u;";
  private static final String FIND_USER_BY_ID_QUERY = "SELECT u.id, u.login, u.firstname, u.lastname FROM users u WHERE id = ?;";
  private static final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT u.id, u.login, u.firstname, u.lastname FROM users u WHERE login = ? AND password = ?;";
  private static final String SAVE_USER_QUERY = "INSERT INTO users (login, password, firstname, lastname, role_id) VALUES (?, ?, ?, ?, ?)";
  private static final String FIND_USER_BY_LOGIN_QUERY = "SELECT u.id, u.login, u.firstname, u.lastname FROM users u WHERE u.login = ?;";
  private final DataBaseConfig dataBaseConfig;

  public UserDaoImpl(final ConnectionPool connectionPool) {
    super(connectionPool);
    dataBaseConfig = new DataBaseConfig();
  }

  public UserRowDto saveUser(UserRow userRow) throws DaoException {
    final List<Object> parameters1 = Collections.singletonList(
        convertNullToEmpty(userRow.getLogin())
    );
    final List<Object> parameters2 = Arrays.asList(
        convertNullToEmpty(userRow.getLogin()),
        convertNullToEmpty(userRow.getPassword()),
        convertNullToEmpty(userRow.getFirstName()),
        convertNullToEmpty(userRow.getLastName()),
        convertNullToEmpty(userRow.getRole_id())
    );
    Connection connection = null;
    PreparedStatement preparedStatement1 = null;
    PreparedStatement preparedStatement2 = null;
    ResultSet resultSet = null;
    int affectedRows = 0;
    try {
      connection = getConnection(false);
      preparedStatement1 = getPreparedStatement(FIND_USER_BY_LOGIN_QUERY, connection, parameters1);
      preparedStatement2 = getPreparedStatement(SAVE_USER_QUERY, connection, parameters2);

      // todo check isolation level
      resultSet = preparedStatement1.executeQuery();
      if (!resultSet.next()) {
        affectedRows = preparedStatement2.executeUpdate();
      }
      connection.commit();

      processAbnormalCase(affectedRows < 1, "User HAS NOT BEEN registered. Such login exists.");
      return new UserRowDto(userRow);
    } catch (SQLException | DaoException e) {
      throw new DaoException(e);
    } finally {
      close(resultSet);
      close(preparedStatement1, preparedStatement2);
      retrieve(connection);
    }
  }

  @Override
  public UserRowDto findUserByLoginAndPassword(UserRow userRow) throws DaoException {
    List<Object> parameters = Arrays.asList(
        userRow.getLogin(),
        userRow.getPassword()
    );
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connection = getConnection(true);
      preparedStatement = getPreparedStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY, connection, parameters);
      resultSet = preparedStatement.executeQuery();

      UserRowDto userRowDto = null;
      while (resultSet.next()) {
        long id = resultSet.getLong(1);
        String login = resultSet.getString(2);
        String firstName = resultSet.getString(3);
        String lastName = resultSet.getString(4);
        userRowDto = new UserRowDto(id, login, firstName, lastName);
      }
      processAbnormalCase(isNull(userRowDto), "No such User.");
      return userRowDto;
    } catch (SQLException | DaoException e) {
      e.printStackTrace();
      throw new DaoException(e);
    } finally {
      close(resultSet);
      close(preparedStatement);
      retrieve(connection);
    }
  }

  @Override
  public List<UserRowDto> getUsers() {
    try (Connection connection = dataBaseConfig.getConnection();
         PreparedStatement preparedStatement = getPreparedStatement(FIND_ALL_USERS_QUERY, connection, Collections.emptyList());
         ResultSet resultSet = preparedStatement.executeQuery();) {
      final List<UserRowDto> users = new ArrayList<>();
      while (resultSet.next()) {
        long id = resultSet.getLong(1);
        String login = resultSet.getString(2);
        String fn = resultSet.getString(3);
        String ln = resultSet.getString(4);
        users.add(new UserRowDto(id, login, fn, ln));
      }
      return users;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public UserRowDto getUserById(Long id) {
    List<Object> parameters = Arrays.asList(
        id
    );
    try (Connection connection = dataBaseConfig.getConnection();
         PreparedStatement preparedStatement = getPreparedStatement(FIND_USER_BY_ID_QUERY, connection, parameters);
         ResultSet resultSet = preparedStatement.executeQuery();) {
      UserRowDto userRowDto = null;
      while (resultSet.next()) {
        long foundId = resultSet.getLong(1);
        String login = resultSet.getString(2);
        String fn = resultSet.getString(3);
        String ln = resultSet.getString(4);
        userRowDto = new UserRowDto(foundId, login, fn, ln);
      }
      return userRowDto;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }


}
  /*@Override
  public UserRowDto findUserByLoginAndPassword(UserRow userRow) {
    List<Object> parameters = Arrays.asList(
        userRow.getLogin(),
        userRow.getPassword()
    );
    try (Connection connection = dataBaseConfig.getConnection();
         PreparedStatement preparedStatement = getPreparedStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY, connection, parameters);
         ResultSet resultSet = preparedStatement.executeQuery();) {
      UserRowDto userRowDto = null;
      while (resultSet.next()) {
        long foundId = resultSet.getLong(1);
        String login = resultSet.getString(2);
        String fn = resultSet.getString(3);
        String ln = resultSet.getString(4);
        userRowDto = new UserRowDto(foundId, login, fn, ln);
      }
      return userRowDto;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }

  @Override
  public UserRowDto saveUser(UserRow userRow) {
    List<Object> parameters = Arrays.asList(
        convertNullToEmpty(String.valueOf(userRow.getId())),
        convertNullToEmpty(userRow.getLogin()),
        convertNullToEmpty(userRow.getPassword()),
        convertNullToEmpty(userRow.getFirstName()),
        convertNullToEmpty(userRow.getLastName()),
        convertNullToEmpty(String.valueOf(userRow.getRole_id()))
    );
    try (Connection connection = dataBaseConfig.getConnection();
         PreparedStatement preparedStatement = getPreparedStatement(SAVE_USER_QUERY, connection, parameters);) {

      int affectedRows = preparedStatement.executeUpdate();
      UserRowDto userRowDto = null;
      if (affectedRows > 0) {
        userRowDto = new UserRowDto(userRow);
      }
      return userRowDto;
    } catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException();
    }
  }*/

/*  private PreparedStatement getPreparedStatement(String query, Connection connection, final List<Object> parameters) throws SQLException {
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    setPreparedStatementParameters(preparedStatement, parameters);
    return preparedStatement;
  }

  private void setPreparedStatementParameters(PreparedStatement preparedStatement, List<Object> parameters) throws SQLException {
    for (int i = 0, queryParameterIndex = 1; i < parameters.size(); i++, queryParameterIndex++) {
      Object parameter = parameters.get(i);
      setPreparedStatementParameter(preparedStatement, queryParameterIndex, parameter);
    }
  }

  private void setPreparedStatementParameter(PreparedStatement preparedStatement, int queryParameterIndex, Object parameter) throws SQLException {
    if (Long.class == parameter.getClass()) {
      preparedStatement.setLong(queryParameterIndex, (Long) parameter);
    } else if (String.class == parameter.getClass()){
      preparedStatement.setString(queryParameterIndex, (String) parameter);
    }
  }*/

/*  public static void main(String[] args) throws SQLException {
    UserDaoImpl dao = new UserDaoImpl(new ConnectionPool() {
      @Override
      public Connection take() throws DaoException {
        return null;
      }

      @Override
      public void retrieve(Connection connection) {

      }
    });
//    dao.saveUser(new User(5L,"5","5","","", Role.UNREGISTERED_USER.getId()));
//    System.out.println(dao.getUserById(2L) + "\n");
//    System.out.println(dao.getUserByLoginAndPassword(new User(null, "alina", "alina123", null, null, null)) + "\n");
    dao.getUsers().forEach(System.out::println);
  }*/
