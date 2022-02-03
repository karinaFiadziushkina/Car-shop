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

public class UpdateProductCommand extends AbstractCommand implements Command {
  private static final Logger LOG = Logger.getLogger(UpdateProductCommand.class.getName());

  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

    LOG.info("UPDATE PRODUCT STARTS.");

    String productIdString = request.getParameter(PRODUCT_ID);
    controllerValidator.numericParameterValidation(productIdString);
    long productId = Long.parseLong(productIdString);
    String lastCommand = "frontController?command=product_info&id=" + productId;
    String message;

    try {
      Product product = getUpdatedProduct(request);
      if (productService.update(product)) {
        lastCommand = "frontController?command=go_to_page&address=main.jsp";
        message = "Product is updated";
        processRequest(request, lastCommand, message);
        response.sendRedirect(lastCommand);
      } else {
        message = "No such product exists";
        processRequest(request, lastCommand, message);
        request.getRequestDispatcher(lastCommand).forward(request, response);
      }
    } catch (Exception e) {
      try {
        request.getSession().setAttribute(MESSAGE, e.getMessage());
        request.getSession().setAttribute(LAST_COMMAND, "frontController?command=go_to_page&address=product_info.jsp&id=" + productId);
        request.getRequestDispatcher(lastCommand).forward(request, response);
        LOG.info(e.getMessage());
      } catch (IOException | ServletException ex) {
        throw new ControllerException(ex);
      }
      throw new ControllerException(e);
    }
  }

  private Product getUpdatedProduct(HttpServletRequest request) throws ControllerException {
    Long id = Long.valueOf(request.getParameter(PRODUCT_ID).trim());
    Long brand_id = Long.valueOf(request.getParameter(PRODUCT_BRAND_ID).trim());
    String model = request.getParameter(PRODUCT_MODEL).trim();
    Long price = Long.valueOf(request.getParameter(PRODUCT_PRICE).trim());
    Long quantity = Long.valueOf(request.getParameter(PRODUCT_QUANTITY).trim());

    controllerValidator.stringParameterValidation(model);
    controllerValidator.longValidation(id, brand_id, price, quantity);

    return new Product(id, brand_id, model, price, quantity);
  }
}
