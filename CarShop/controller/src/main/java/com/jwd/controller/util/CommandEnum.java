package com.jwd.controller.util;

public enum CommandEnum {
  DEFAULT("default"),
  REGISTRATION("registration"),
  LOGIN("login"),
  LOGOUT("logout"),
  SHOW_PRODUCTS("show_products");
//    CHANGE_LANGUAGE("change_language"),
//    SHOW_USERS("show_Users"),

  private final String frontEndName;

  CommandEnum(final String frontEndName) {
    this.frontEndName = frontEndName;
  }

  public String getFrontEndName() {
    return frontEndName;
  }
}
