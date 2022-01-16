package com.jwd.dao.entity;

public class User {

  private Long id;
  private String login;
  private String firstName;
  private String lastName;
  private String password; // hashed
  private Long role_id;


  public User() {
  }

  public User(Long id, String login, String password, String firstName, String lastName, Long role_id) {
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

  public Long getRole_id() {
    return role_id;
  }

  public void setRole_id(Long role_id) {
    this.role_id = role_id;
  }

 /* @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User userImpl = (User) o;

    if (id != null ? !id.equals(userImpl.id) : userImpl.id != null) return false;
    if (login != null ? !login.equals(userImpl.login) : userImpl.login != null) return false;
    if (firstName != null ? !firstName.equals(userImpl.firstName) : userImpl.firstName != null) return false;
    if (lastName != null ? !lastName.equals(userImpl.lastName) : userImpl.lastName != null) return false;
    if (role_id != null ? !role_id.equals(userImpl.role_id) : userImpl.role_id != null) return false;
    return password != null ? password.equals(userImpl.password) : userImpl.password == null;

  }
*/
  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return role_id == user.role_id && id.equals(user.id) && login.equals(user.login) && firstName.equals(user.firstName) && lastName.equals(user.lastName) && password.equals(user.password);
  }

}
