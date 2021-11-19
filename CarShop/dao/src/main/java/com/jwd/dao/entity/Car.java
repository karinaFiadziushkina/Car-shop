package com.jwd.dao.entity;

import java.math.BigDecimal;

public class Car {

  private Long id;
  private Long brand_id;
  private String model;
  private Long price;
  private Long quantity;

  public Car(Long id, Long brand_id, String model, Long price, Long quantity) {
    this.id = id;
    this.brand_id = brand_id;
    this.model = model;
    this.price = price;
    this.quantity = quantity;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getBrand_id() {
    return brand_id;
  }

  public void setBrand_id(Long brand_id) {
    this.brand_id = brand_id;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }
}
