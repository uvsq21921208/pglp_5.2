package uvsq.pglp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class Dao<T> {
  public Connection connect = null;

  public Statement stmt = null;
 
  public abstract T create(T obj);

  public abstract T find(String id);

  public abstract T update(T obj);

  public abstract void delete(T obj);
  public Connection connect() {

	    try {
	      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
	      connect = DriverManager.getConnection("jdbc:derby:test;create=true");
	    } catch (ClassNotFoundException | SQLException e) {
	      e.printStackTrace();
	      try {
	        connect.close();
	      } catch (SQLException ex) {
	        ex.printStackTrace();
	      }
	    }
	    return connect;
	  }

	  public void disconnect() {

	    try {
	      connect.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
}  
