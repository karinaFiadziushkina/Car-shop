package com.jwd.service.entity;

import java.util.Objects;

public class Product {
  private Long id;
  private Long brand_id;
  private String model;
  private Long price;
  private Long quantity;

  public Product(Long id, Long brand_id, String model, Long price, Long quantity) {
    this.id = id;
    this.brand_id = brand_id;
    this.model = model;
    this.price = price;
    this.quantity = quantity;
  }

  public Product() {

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Product product = (Product) o;

    if (id != null ? !id.equals(product.id) : product.id != null) return false;
    if (brand_id != null ? !brand_id.equals(product.brand_id) : product.brand_id != null) return false;
    if (model != null ? !model.equals(product.model) : product.model != null) return false;
    if (price != null ? !price.equals(product.price) : product.price != null) return false;
    return quantity != null ? quantity.equals(product.quantity) : product.quantity == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (brand_id != null ? brand_id.hashCode() : 0);
    result = 31 * result + (model != null ? model.hashCode() : 0);
    result = 31 * result + (price != null ? price.hashCode() : 0);
    result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", brand_id=" + brand_id +
        ", model='" + model + '\'' +
        ", price=" + price +
        ", quantity=" + quantity +
        '}';
  }
}
