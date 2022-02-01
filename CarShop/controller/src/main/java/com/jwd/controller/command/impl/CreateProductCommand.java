package com.jwd.controller.command.impl;

import com.jwd.controller.command.AbstractCommand;
import com.jwd.controller.command.Command;
import com.jwd.controller.exception.ControllerException;
import com.jwd.service.ServiceFactory;
import com.jwd.service.entity.Product;
import com.jwd.service.logic.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static com.jwd.controller.util.Constant.*;

public class CreateProductCommand extends AbstractCommand implements Command {
  private static final Logger LOG = Logger.getLogger(CreateProductCommand.class.getName());

  private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
  private final ProductService productService = serviceFactory.getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("CREATE PRODUCT STARTS.");

    String lastCommand = "/new_product";
    String message;
    try {
      Product product = getProductFromClient(request);
      if (productService.create(product)) {
        lastCommand = "/main";
        message = "Product is created";
        processRequest(request, lastCommand, message);
        response.sendRedirect(lastCommand);
      } else {
        message = "Product with such title already exists!";
        processRequest(request, lastCommand, message);
        request.getRequestDispatcher(lastCommand).forward(request, response);
      }
    } catch (Exception e) {
      try {
        request.getSession().setAttribute(MESSAGE, e.getMessage());
        request.getSession().setAttribute(LAST_COMMAND, "/main");
        request.getRequestDispatcher(lastCommand).forward(request, response);
        LOG.info(e.getMessage());
      } catch (ServletException | IOException ex) {
        throw new ControllerException(ex);
      }
      throw new ControllerException(e);
    }
  }

  private Product getProductFromClient(HttpServletRequest request) throws ControllerException {
    Long brand_id = Long.valueOf(request.getParameter(PRODUCT_BRAND_ID).trim());
    String model = request.getParameter(PRODUCT_MODEL).trim();
    Long price = Long.valueOf(request.getParameter(PRODUCT_PRICE).trim());
    Long quantity = Long.valueOf(request.getParameter(PRODUCT_QUANTITY).trim());

    controllerValidator.stringParameterValidation(model);
    controllerValidator.longValidation(brand_id, price, quantity);

    return new Product(brand_id, model, price, quantity);
  }
}
