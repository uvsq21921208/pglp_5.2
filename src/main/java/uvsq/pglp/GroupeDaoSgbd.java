package uvsq.pglp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupeDaoSgbd extends Dao<CompositePersonnel> {

  @Override
  public CompositePersonnel create(CompositePersonnel obj) {

    int i = -1;
    this.connect();
    PreparedStatement insert = null;
    try {
      String sql = "insert into Groupes(groupeid) values (?)";
      insert = this.connect.prepareStatement(sql);
      insert.setInt(1, obj.getID());
      i = insert.executeUpdate();
      
      DaoFactorySgbd ds = new DaoFactorySgbd();
      Dao<Personnel> pd = ds.createPersonnelDao();
      for (TypePersonnel p: obj.getAllPersonnel()) {
        if (p.isGroupe()) {
          pd.create((Personnel) p);
        }
      }

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
    //this.disconnect();
    if (i > 0) {
      return obj;
    } else {
      return null;
    }
  }

  @Override
  public CompositePersonnel find(String id) {
    CompositePersonnel obj = null;
    this.connect();
    PreparedStatement select = null;
    try {
      String sql = "select * from Groupes where groupeid = (?)";
      int groupeid = Integer.parseInt(id);
      select = connect.prepareStatement(sql);
      select.setInt(1, groupeid);
      select.execute();
      ResultSet result = select.getResultSet();
      if (result.next()) {
        obj = new CompositePersonnel(result.getInt(1));
        PersonnelDaoSgbd pd = new PersonnelDaoSgbd();
        ArrayList<Personnel> personnels = pd.getPersonnels(obj.getID());

        for (Personnel p: personnels) {
          obj.add(p);
        }
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
    return obj;
  }

  @Override
  public CompositePersonnel update(CompositePersonnel obj) {
    this.connect();
    PreparedStatement updateGroupe = null;
    try {
      updateGroupe =
          this.connect.prepareStatement("update Groupes set groupeid = (?)");
      updateGroupe.setInt(1, obj.getID());
      DaoFactorySgbd dao = new DaoFactorySgbd();
      Dao<Personnel> daoP = dao.createPersonnelDao();
      for (TypePersonnel p: obj.getAllPersonnel()) {
        if (daoP.find(((Personnel) p).getNom()) != null) {
          daoP.update((Personnel) p);
        } else {
          daoP.create((Personnel) p);
        }
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } 
    try {
      if (updateGroupe != null) {
        updateGroupe.close();
      } 
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    //this.disconnect();
    return obj;
  }

  @Override
  public void delete(CompositePersonnel obj) {
    this.connect();
    PreparedStatement delete = null;
    try {
      DaoFactorySgbd dao = new DaoFactorySgbd();
      Dao<Personnel> daoP = dao.createPersonnelDao();
      for (TypePersonnel p: obj.getAllPersonnel()) {

        if (!p.isGroupe()) {
          daoP.delete((Personnel) p);
        }
      }
      String sql = "Delete from Groupes where groupeid = (?)";
      int groupeid = obj.getID();
      delete = connect.prepareStatement(sql);
      delete.setInt(1, groupeid);
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

}