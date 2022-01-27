package com.jwd.dao.entity;

public class UserRow {

  private Long id;
  private String login;
  private String firstName;
  private String lastName;
  private String password; // hashed
  private String role_id;


  public UserRow() {
  }

  public UserRow(Long id, String login, String password, String firstName, String lastName, String role_id) {
    this.id = id;
    this.login = login;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
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

    UserRow userRow = (UserRow) o;

    if (id != null ? !id.equals(userRow.id) : userRow.id != null) return false;
    if (login != null ? !login.equals(userRow.login) : userRow.login != null) return false;
    if (firstName != null ? !firstName.equals(userRow.firstName) : userRow.firstName != null) return false;
    if (lastName != null ? !lastName.equals(userRow.lastName) : userRow.lastName != null) return false;
    if (role_id != null ? !role_id.equals(userRow.role_id) : userRow.role_id != null) return false;
    return password != null ? password.equals(userRow.password) : userRow.password == null;

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

}
