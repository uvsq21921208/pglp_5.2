package uvsq.pglp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonnelDaoSgbd extends Dao<Personnel> {

  @Override
  public Personnel create(Personnel obj) {
    this.connect();
    PreparedStatement insert = null;
    int i = -1;
    try {
      insert = this.connect.prepareStatement("Insert into Personnels"
          + "(nom, prenom, fonction,groupeid)"
          + " values (?,?,?,?)");
      insert.setString(1, obj.getNom());
      insert.setString(2, obj.getPrenom());
      insert.setString(3, obj.getFonction());
      insert.setInt(4, obj.getGroupeId());
      i = insert.executeUpdate();
     

    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (insert != null) {
        insert.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    if (i > 0) {
      return obj;
    } else {
      return null;
    }
  }

  @Override
  public Personnel find(String id) {
    Personnel p = null;
    this.connect();
    PreparedStatement select = null;
    try {
      select = this.connect.prepareStatement("select * from personnels" 
          + " where nom = (?)");
      select.setString(1, id);
      select.execute();
      ResultSet result = select.getResultSet();
      if (result.next()) {
        String nom = result.getString("nom");
        String prenom = result.getString("prenom");
        String fonction = result.getString("Fonction");
        int groupeid = result.getInt("groupeid");
        p = new Personnel.PersonelBuilder(nom, prenom, fonction).groupeId(groupeid).build();
        select.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (select != null) {
        select.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    //this.disconnect();
    return p;

  }

  @Override
  public Personnel update(Personnel obj) {
    this.connect();
    PreparedStatement update = null;
    try {
      update = this.connect.prepareStatement("update Personnels"
          + " set nom = (?), prenom = (?), fonction = (?), groupeid = (?)" 
          + " where nom = (?) ");
      update.setString(1, obj.getNom());
      update.setString(2, obj.getPrenom());
      update.setString(3, obj.getFonction());
      update.setInt(4, obj.getGroupeId());
      update.setString(5, obj.getNom());
      update.executeUpdate();
      update.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (update != null) {
        update.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return obj;
  }

  @Override
  public void delete(Personnel obj) {
    this.connect();
    PreparedStatement delete = null;
    try {
      delete = this.connect.prepareStatement("delete from Personnels " 
          + "where nom = (?)");
      delete.setString(1, obj.getNom());
      delete.execute();
      
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (delete != null) {
        delete.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
  /**
   * get all personnels.
   * @return Array list that contains all personnels.
   */

  public ArrayList<Personnel> getPersonnels(int groupeId) {

    ArrayList<Personnel> personnels = new ArrayList<Personnel>();
    this.connect();
    PreparedStatement select = null;
    try {
      select = this.connect.prepareStatement("Select * from Personnels "
          + "where groupeid = (?)");
      select.setInt(1, groupeId);
      select.execute();
      ResultSet result = select.getResultSet();

      while (result.next()) {
        String nom = result.getString("nom");
        String prenom = result.getString("prenom");
        String fonction = result.getString("fonction");
        Personnel p = new Personnel.PersonelBuilder(nom, prenom, fonction).build();
        personnels.add(p);
      }
    } catch (SQLException e) {

      e.printStackTrace();
    }
    try {
      if (select != null) {
        select.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    

    //this.disconnect();
    return personnels;
  }
}