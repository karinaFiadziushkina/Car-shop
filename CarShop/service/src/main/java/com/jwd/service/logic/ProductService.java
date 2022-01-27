package com.jwd.service.logic;

import com.jwd.service.entity.Page;
import com.jwd.service.entity.Product;
import com.jwd.service.exception.ServiceException;

public interface ProductService {
  Page<Product> showProducts(Page<Product> productPageRequest) throws ServiceException;
}
