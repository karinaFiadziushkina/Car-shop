package com.jwd.controller.command.impl;

import com.jwd.controller.command.AbstractCommand;
import com.jwd.controller.command.Command;
import com.jwd.controller.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static com.jwd.controller.util.Constant.ADDRESS;

public class GoToPageCommand extends AbstractCommand implements Command {
  private static final Logger LOG = Logger.getLogger(GoToPageCommand.class.getName());

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

    LOG.info("Start in GoToPageCommand");

    try {
      String goToPage = "/index.jsp".equals(request.getParameter(ADDRESS)) ? "/index.jsp" : "/WEB-INF/jsp/" + request.getParameter(ADDRESS);
      request.getRequestDispatcher(goToPage).forward(request, response);
    } catch (IOException | ServletException e) {
      throw new ControllerException(e);
    }
  }
}
