package com.jwd.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.jwd.controller.util.CommandEnum.*;
import static com.jwd.controller.util.CommandEnum.LOGIN;
import static com.jwd.controller.util.Constant.*;

public class AuthFilter implements Filter{
  private static final Logger LOGGER = Logger.getLogger(AuthFilter.class.getName());

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;
    final List<String> alwaysAvailableCommands = Arrays.asList(
        DEFAULT.getFrontEndName(),
        REGISTRATION.getFrontEndName(),
        LOGIN.getFrontEndName(),
        LOGOUT.getFrontEndName()
        /*SHOW_PRODUCTS.getFrontEndName(),
        DELETE_PRODUCT.getFrontEndName(),
        CREATE_PRODUCT.getFrontEndName(),
        UPDATE_PRODUCT.getFrontEndName(),
        PRODUCT_INFO.getFrontEndName()*/
    );
    if (alwaysAvailableCommands.contains(req.getParameter(COMMAND)) ||
        req.getSession().getAttribute(ROLE) != null) {
      LOGGER.info("================PROCESSED");
      chain.doFilter(request, response);
    } else {
      LOGGER.info("================REDIRECTED");
      res.sendRedirect(HOME);
    }
  }

  @Override
  public void destroy() {
  }
}
