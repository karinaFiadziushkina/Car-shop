package com.jwd.service.logic;

import com.jwd.service.entity.User;
import com.jwd.service.entity.UserDto;
import com.jwd.service.exception.ServiceException;

public interface UserService {
  UserDto registerUser(User user) throws ServiceException;

  UserDto login(User user) throws ServiceException;
}
