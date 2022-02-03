package com.jwd.controller.util;

public enum CommandEnum {
  DEFAULT("default"),
  REGISTRATION("registration"),
  LOGIN("login"),
  LOGOUT("logout"),
  DELETE_PRODUCT("delete_product"),
  CREATE_PRODUCT("create_product"),
  UPDATE_PRODUCT("update_product"),
  PRODUCT_INFO("product_info"),
  GO_TO_PAGE("go_to_page"),
  SHOW_PRODUCTS("show_products");

  private final String frontEndName;

  CommandEnum(final String frontEndName) {
    this.frontEndName = frontEndName;
  }

  public String getFrontEndName() {
    return frontEndName;
  }
}
