package com.jwd.controller.command;

import com.jwd.controller.validator.ControllerValidator;

import javax.servlet.http.HttpServletRequest;

import static com.jwd.controller.util.Constant.LAST_COMMAND;
import static com.jwd.controller.util.Constant.MESSAGE;

public abstract class AbstractCommand implements Command{

  protected static final ControllerValidator controllerValidator = ControllerValidator.getInstance();

  protected void processRequest(HttpServletRequest request, String lastCommand, String message) {
    request.getSession().setAttribute(LAST_COMMAND, lastCommand);
    request.getSession().setAttribute(MESSAGE, message);
  }

}
