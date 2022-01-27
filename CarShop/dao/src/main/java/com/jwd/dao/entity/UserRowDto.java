package com.jwd.dao.entity;

public class UserRowDto {

  private Long id;
  private String login;
  private String firstName;
  private String lastName;

  public UserRowDto() {
  }

  public UserRowDto(Long id, String login, String firstName, String lastName) {
    this.id = id;
    this.login = login;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public UserRowDto(UserRow userRow) {
    id = userRow.getId();
    login = userRow.getLogin();
    firstName = userRow.getFirstName();
    lastName = userRow.getLastName();
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserRowDto userRowDto = (UserRowDto) o;

    if (id != null ? !id.equals(userRowDto.id) : userRowDto.id != null) return false;
    if (login != null ? !login.equals(userRowDto.login) : userRowDto.login != null) return false;
    if (firstName != null ? !firstName.equals(userRowDto.firstName) : userRowDto.firstName != null) return false;
    return lastName != null ? lastName.equals(userRowDto.lastName) : userRowDto.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (login != null ? login.hashCode() : 0);
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "UserDto{" +
        "id=" + id +
        ", login='" + login + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}
