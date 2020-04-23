package uvsq.pglp;

import java.sql.SQLException;
import java.sql.Statement;

public class AnnuaireDaoSgbd extends Dao<Annuaire> {
  Statement statement;

  @Override
  public Annuaire create(Annuaire obj) {

    this.connect();
    try {
      String sql = "CREATE TABLE Groupes(groupeid INTEGER primary key not null)";
      statement = connect.createStatement();
      statement.execute(sql);
      sql = "CREATE TABLE Personnels(nom varchar(20) PRIMARY KEY NOT NULL, " 
         + " prenom  varchar(20), fonction varchar(20)," 
         + "groupeid integer references Groupes(groupeid))";
      statement.execute(sql);
      statement.close();
    } catch (SQLException e) {

      e.printStackTrace();
    }
    //this.disconnect();
    return obj;
  }

  @Override
  public Annuaire find(String id) {
    return null;

  }

  @Override
  public Annuaire update(Annuaire obj) {
    return null;
  }

  @Override
  public void delete(Annuaire obj) {

    try {
      statement = connect.createStatement();
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    String sql = "drop table personnels";
    try {
      statement.execute(sql);
    } catch (SQLException e1) {
      e1.printStackTrace();
    }
    try {
      sql = "drop table groupes";
      statement.execute(sql);
      statement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }


  }

}