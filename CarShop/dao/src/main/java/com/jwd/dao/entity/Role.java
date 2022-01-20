package com.jwd.dao.entity;

public enum Role {
  ADMIN(1L, "admin"),
  REGISTERED_USER(2L, "registered");

  private String name;
  private Long id;

  Role(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Long getId() {
    return id;
  }
}
