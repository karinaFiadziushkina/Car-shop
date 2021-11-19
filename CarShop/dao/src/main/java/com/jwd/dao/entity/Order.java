package com.jwd.dao.entity;

public class Order {

  private Long id;
  private Long user_id;
  private Long car_id;

  public Order(Long id, Long user_id, Long car_id) {
    this.id = id;
    this.user_id = user_id;
    this.car_id = car_id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getCar_id() {
    return car_id;
  }

  public void setCar_id(Long car_id) {
    this.car_id = car_id;
  }
}
