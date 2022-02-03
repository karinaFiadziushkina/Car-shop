package com.jwd.controller.validator;

import com.jwd.controller.exception.ControllerException;
import com.jwd.service.entity.Page;
import com.jwd.service.entity.Product;

import java.util.Arrays;

import static com.jwd.controller.util.Util.isNullOrEmpty;
import static java.util.Objects.isNull;


public class ControllerValidator {
  private static final ControllerValidator INSTANCE = new ControllerValidator();

  public ControllerValidator() {
  }

  public static ControllerValidator getInstance() {
    return INSTANCE;
  }

  public void isValidLogin(final String login) throws ControllerException {
    if (isNullOrEmpty(login)) {
      throw new ControllerException("Login is null or empty.");
    }
  }

  public void isValidPassword(final char[] password) throws ControllerException {
    if (isNullOrEmpty(password)) {
      throw new ControllerException("Password is null or empty.");
    }
  }

  public void isValidPassword(final char[] password1, final char[] password2) throws ControllerException {
    if (isNullOrEmpty(password1)) {
      throw new ControllerException("Password is null or empty.");
    } else if (!Arrays.equals(password1, password2)) {
      throw new ControllerException("Passwords are not equal.");
    }
  }

  public void numericParameterValidation(String... numericParameters) throws ControllerException {
    for (String numericParameter : numericParameters) {
      if (!isNumeric(numericParameter)) {
        throw new ControllerException("Invalid numeric parameter");
      }
    }
  }

  public void stringParameterValidation(String... parameters) throws ControllerException {
    for (String parameter : parameters) {
      if (isNull(parameter) || parameter.isEmpty()) {
        throw new ControllerException("Model can't be empty");
      }
    }

  }

  public void stringParameterValidationNonNull(String... parameters) throws ControllerException {
    for (String parameter : parameters) {
      if (isNull(parameter)) {
        throw new ControllerException("Invalid input parameter");
      }
    }

  }

  public void longValidation(Long... ids) throws ControllerException {
    for (Long id : ids) {
      if (isNull(id) || id < 0) {
        throw new ControllerException("Invalid id.");
      }
    }

  }

  public void pageValidation(Page<Product> carPageRequest) throws ControllerException {
    boolean isValidPage =
        carPageRequest.getPageNumber() > 0
            && !carPageRequest.getDirection().isEmpty()
            && !carPageRequest.getSortBy().isEmpty();
    if (!isValidPage) {
      throw new ControllerException("CarPageRequest is null");
    }
  }

  private boolean isNumeric(String parameter) {
    boolean isNumeric = true;
    try {
      Long.parseLong(parameter);
    } catch (NumberFormatException e) {
      isNumeric = false;
    }
    return isNumeric;
  }
}
