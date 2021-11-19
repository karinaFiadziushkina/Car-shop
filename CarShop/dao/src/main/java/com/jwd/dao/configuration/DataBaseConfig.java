package com.jwd.dao.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class DataBaseConfig {

  private static final String DATABASE_CONFIG_PATH = "db.properties";
  private final String
      URL = "jdbc:postgresql://localhost/",
      USERNAME = "postgres",
      DATABASE_NAME = "Car shop",
      PASSWORD = "root",
      DRIVER = "database.driver";
  private Properties properties;

  private boolean  driverIsLoaded = false;

/*  public DataBaseConfig() {
    loadProperties();
  }*/

  public Connection getConnection() throws SQLException {
//    loadJdbcDriver();
    Connection connection;
    Properties properties = new Properties();
    properties.setProperty("user", USERNAME);
    properties.setProperty("password", PASSWORD);
    connection = DriverManager.getConnection(URL + DATABASE_NAME, properties);
    return connection;

  }

 /* public String getProperty(String key) {
    return properties.getProperty(key);
  }*/

 /* private void loadJdbcDriver() {
    if (!driverIsLoaded) {
      try {
        Class.forName(getProperty(DRIVER));
        driverIsLoaded = true;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }*/

/*  private void loadProperties() {
    try(InputStream is = DataBaseConfig.class.getClassLoader().getResourceAsStream(DATABASE_CONFIG_PATH)) {
      System.out.println(is);
      properties = new Properties();
      properties.load(is);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }*/

}
