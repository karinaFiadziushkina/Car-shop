package com.jwd.dao.repository;

import com.jwd.dao.entity.User;
import com.jwd.dao.entity.UserDto;
import com.jwd.dao.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

  /**
   * returns all users in our app
   * @return List<UserDto>
   */

  List<UserDto> getUsers();
  UserDto getUserById(Long id);
  UserDto getUserByLoginAndPassword(User user);


  /**
   *
   * @param user - to be saved, received from UI
   * @return UserDto to display saved user
   */
  UserDto saveUser(User user) throws DaoException;
}
