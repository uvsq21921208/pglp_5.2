package uvsq.pglp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupeDaoSGBD extends Dao<CompositePersonnel>{

	@Override
	public CompositePersonnel create(CompositePersonnel obj) {
		// TODO Auto-generated method stub
		 int i = -1;
		 this.connect();
		 try {
			   String sql = "insert into Groupes(groupeid) values (?)";
			   PreparedStatement insert = this.connect.prepareStatement(sql);
			   insert.setInt(1, obj.getID());
			   i = insert.executeUpdate();
			   insert.close();
			   DaoFactorySGBD ds = new DaoFactorySGBD();
			   Dao<Personnel> pd = ds.createPersonnelDao();
			   for (TypePersonnel p : obj.getAllPersonnel()) {
				   if (p.isGroupe()) {
					   pd.create((Personnel)p);
				   }
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
		 try {
			   String sql = "select * from Groupes where groupeid = (?)";
			   int groupeid = Integer.parseInt(id);
			   PreparedStatement select = connect.prepareStatement(sql);
			   select.setInt(1, groupeid);
			   select.execute();
			   ResultSet result = select.getResultSet();
			   result.next();
			   obj = new CompositePersonnel(result.getInt(1));
			   PersonnelDaoSGBD pd = new PersonnelDaoSGBD(); 
			   ArrayList<Personnel> personnels = pd.getPersonnels(obj.getID());
			   
			   for(Personnel p : personnels ) {
				   obj.add(p);
			   }
			   select.close();
		} catch (SQLException e) {
			   e.printStackTrace();
		}
		//this.disconnect();
		return obj;
	}

	@Override
	public CompositePersonnel update(CompositePersonnel obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CompositePersonnel obj) {
		// TODO Auto-generated method stub
		
	}

}
