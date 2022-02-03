package com.jwd.controller.command.impl;

import com.jwd.controller.command.AbstractCommand;
import com.jwd.controller.command.Command;
import com.jwd.controller.exception.ControllerException;
import com.jwd.service.ServiceFactory;
import com.jwd.service.exception.ServiceException;
import com.jwd.service.logic.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static com.jwd.controller.util.Constant.PRODUCT_ID;

public class DeleteProductCommand extends AbstractCommand implements Command {

  private static final Logger LOG = Logger.getLogger(DeleteProductCommand.class.getName());

  private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
  private final ProductService productService = serviceFactory.getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

    LOG.info("DELETE PRODUCT STARTS.");
    String lastCommand;
    String message;
    try {
      String productIdString = request.getParameter(PRODUCT_ID);
      Long productId = Long.valueOf(request.getParameter(PRODUCT_ID));
      controllerValidator.numericParameterValidation(productIdString);

      if (productService.delete(productId)) {
        lastCommand = "/main";
        message = "Product is deleted";
        processRequest(request, lastCommand, message);
        response.sendRedirect(lastCommand);
      } else {
        lastCommand = "/main";
        message = "No such product exists";
        processRequest(request, lastCommand, message);
        request.getRequestDispatcher(lastCommand).forward(request, response);
      }
    }  catch (IOException | ServiceException | ServletException e) {
      throw new ControllerException(e);
    }
  }
}
