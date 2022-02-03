package com.jwd.service.validator;

import com.jwd.service.entity.Page;
import com.jwd.service.entity.Product;
import com.jwd.service.entity.User;
import com.jwd.service.exception.ServiceException;

import static com.jwd.service.util.Util.isNullOrEmpty;
import static java.util.Objects.isNull;

public class ServiceValidator {
  public void validate(User user) throws ServiceException {
    isValidUser(user);
    isValidString(user.getLogin(), "Login");
    isValidString(user.getPassword(), "Password");
  }

  private void isValidUser(User user) throws ServiceException {
    if (isNull(user)) {
      throw new ServiceException("User is null.");
    }
  }

  public void validation(long id) throws ServiceException {
    if (id < 0) {
      throw new ServiceException("Invalid id.");
    }
  }

  public void validation(Product product) throws ServiceException {
    if (isNull(product)) {
      throw new ServiceException("Null Product");
    } else if ("".equals(product.getModel()) && product.getBrand_id() == null) {
      throw new ServiceException("invalid book");
    }
  }

  public void isValidString(final String string, final String subject) throws ServiceException {
    if (isNullOrEmpty(string)) {
      throw new ServiceException(subject + " is null or empty.");
    }
  }

  public void validate(final Page<Product> productPageRequest) throws ServiceException {
    if (isNull(productPageRequest)) {
      throw new ServiceException("ProductPageRequest is null or empty.");
    }
  }
}
