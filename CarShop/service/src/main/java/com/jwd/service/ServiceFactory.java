package com.jwd.service;

import com.jwd.dao.DaoFactory;
import com.jwd.service.logic.ProductService;
import com.jwd.service.logic.UserService;
import com.jwd.service.logic.impl.ProductServiceImpl;
import com.jwd.service.logic.impl.UserServiceImpl;

public class ServiceFactory {

  private static final ServiceFactory INSTANCE = new ServiceFactory();

  private final UserService userService = new UserServiceImpl();
  private final ProductService productService = new ProductServiceImpl();

  private ServiceFactory() {}

  public static ServiceFactory getInstance() {
    return INSTANCE;
  }

  public UserService getUserService() {
    return userService;
  }

  public ProductService getProductService() {
    return productService;
  }
}
