package com.jwd.dao.entity;

public enum Role {
  UNREGISTERED_USER(0, "non-registered"),
  ADMIN(1, "admin"),
  REGISTERED_USER(2, "registered");

  private String name;
  private int id;

  Role(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }
}
