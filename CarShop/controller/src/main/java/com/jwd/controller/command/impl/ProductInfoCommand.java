package com.jwd.controller.command.impl;

import com.jwd.controller.command.AbstractCommand;
import com.jwd.controller.command.Command;
import com.jwd.controller.exception.ControllerException;
import com.jwd.service.ServiceFactory;
import com.jwd.service.entity.Product;
import com.jwd.service.exception.ServiceException;
import com.jwd.service.logic.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static com.jwd.controller.util.Constant.*;
import static java.util.Objects.nonNull;

public class ProductInfoCommand extends AbstractCommand implements Command {
  private static final Logger LOG = Logger.getLogger(ProductInfoCommand.class.getName());

  private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
  private final ProductService productService = serviceFactory.getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

    LOG.info("PRODUCT INFO STARTS.");

    String userId = request.getParameter(ID);
    controllerValidator.numericParameterValidation(userId);
    String message;
    String lastCommand = "/main";

    try {
      Product product = productService.findById(Long.parseLong(userId));

      if (nonNull(product)) {
        message = (String) request.getSession().getAttribute(MESSAGE);
        request.setAttribute(PRODUCT, product);
      } else {
        message = "No such product was found";
      }
      processRequest(request, lastCommand, message);
      request.getRequestDispatcher("frontController?command=go_to_page&address=product_info.jsp").forward(request, response);

    } catch (ServiceException | IOException | ServletException e) {
      throw new ControllerException(e);
    }
  }
}
