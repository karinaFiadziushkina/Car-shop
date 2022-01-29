package com.jwd.controller.command.impl;

import com.jwd.controller.command.Command;
import com.jwd.controller.exception.ControllerException;
import com.jwd.service.ServiceFactory;
import com.jwd.service.entity.Page;
import com.jwd.service.entity.Product;
import com.jwd.service.logic.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static com.jwd.controller.util.Constant.*;
import static com.jwd.controller.util.Util.*;

public class ShowProductsCommand extends AbstractPageProcessor implements Command {
  private static final Logger LOGGER = Logger.getLogger(ShowProductsCommand.class.getName());

  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOGGER.info("SHOW PRODUCTS STARTS.");
    try {
      // prepare data
      String currentPageParam = request.getParameter(CURRENT_PAGE);
      if (isNullOrEmpty(currentPageParam)) {
        currentPageParam = "1";
      }
      String currentLimitParam = request.getParameter(PAGE_LIMIT);
      if (isNullOrEmpty(currentLimitParam)) {
        currentLimitParam = "5";
      }
      int currentPage = Integer.parseInt(currentPageParam);
      int pageLimit = Integer.parseInt(currentLimitParam);
      final Page<Product> pageRequest = new Page<>();
      pageRequest.setPageNumber(currentPage);
      pageRequest.setLimit(pageLimit);

      // validation
      // ... pageRequest - validate before passing to next layer

      // business logic
      final Page<Product> pageable = productService.showProducts(pageRequest);

      // send response
      request.setAttribute(PAGEABLE, pageable);
      request.getRequestDispatcher(pathToJsp(Command.prepareUri(request))).forward(request, response);
    } catch (Exception e) {
      throw new ControllerException(e);
    }
  }

 /* @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOGGER.info("SHOW PRODUCTS STARTS.");
    try {
      // prepare data
      final Page<Product> pageRequest = new Page<>();
      pageRequest.setFilter(prepareFilter(request));
      pageRequest.setPageNumber(prepareCurrentPage(request));
      pageRequest.setLimit(prepareCurrentLimit(request));

      // validation
      // ... pageRequest - validate before passing to next layer

      // business logic
      final Page<Product> pageable = productService.showProducts(pageRequest);

      // send response
      request.setAttribute(PAGEABLE, pageable);
      request.getRequestDispatcher(pathToJsp(Command.prepareUri(request))).forward(request, response);
    } catch (Exception e) {
      throw new ControllerException(e);
    }
  }

  private Product prepareFilter(final HttpServletRequest request) {
    Product filter = new Product();
    filter.setBrand_id(Long.valueOf(convertNullToEmpty(request.getParameter(PRODUCT_BRAND_ID))));
    filter.setModel(convertNullToEmpty(request.getParameter(PRODUCT_MODEL)));
    filter.setPrice(Long.valueOf(convertNullToEmpty(request.getParameter(PRODUCT_PRICE))));
    filter.setQuantity(Long.valueOf(convertNullToEmpty(request.getParameter(PRODUCT_QUANTITY))));
    return filter;
  }*/
}
