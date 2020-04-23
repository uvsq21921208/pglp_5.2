package uvsq.pglp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao<T> {
  public Connection connect = null;


  public abstract T create(T obj);

  public abstract T find(String id);

  public abstract T update(T obj);

  public abstract void delete(T obj);

  /**
  * Connect to database.
  */

  public void connect() {

    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      connect = DriverManager.getConnection("jdbc:derby:mydb;create=true");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      try {
        connect.close();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }

  }

  /**
  * Disonnects from database.
  */
  public void disconnect() {

    try {
      connect.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}