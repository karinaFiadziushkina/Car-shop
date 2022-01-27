package com.jwd.controller.util;

public enum CommandEnum {
  DEFAULT("default"),
/*  LOGOUT("logout"),
    LOGIN("login"),
    CHANGE_LANGUAGE("change_language"),
    SHOW_USERS("show_Users"),
*/
  REGISTRATION("registration"),
  SHOW_PRODUCTS("show_products");

  private final String frontEndName;

  CommandEnum(final String frontEndName) {
    this.frontEndName = frontEndName;
  }

  public String getFrontEndName() {
    return frontEndName;
  }
}
