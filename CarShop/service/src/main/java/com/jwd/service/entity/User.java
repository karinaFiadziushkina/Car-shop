package com.jwd.service.entity;

public class User {
  private Long id;
  private String login;
  private String firstName;
  private String lastName;
  private String password; // hashed
  private String role_id;

  public User() {
  }

  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public User(Long id, String login, String password, String firstName, String lastName, String role_id) {
    this.id = id;
    this.login = login;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role_id = role_id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole_id() {
    return role_id;
  }

  public void setRole_id(String role_id) {
    this.role_id = role_id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    if (id != null ? !id.equals(user.id) : user.id != null) return false;
    if (login != null ? !login.equals(user.login) : user.login != null) return false;
    if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
    if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
    if (password != null ? !password.equals(user.password) : user.password != null) return false;
    return role_id != null ? role_id.equals(user.role_id) : user.role_id == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    result = 31 * result + (role_id != null ? role_id.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", login='" + login + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", password='" + password + '\'' +
        ", role_id=" + role_id +
        '}';
  }
}
