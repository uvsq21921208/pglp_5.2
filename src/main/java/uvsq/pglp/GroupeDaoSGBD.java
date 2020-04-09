package uvsq.pglp;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupeDaoSGBD extends Dao<CompositePersonnel>{

	@Override
	public CompositePersonnel create(CompositePersonnel obj) {
		// TODO Auto-generated method stub
		 try {
			   String sql = "insert into Groupes(id) values (?)";
			   PreparedStatement insert = connect.prepareStatement(sql);
			   insert.setInt(1, obj.getID());
			   insert.execute();
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
		return obj;
	}

	@Override
	public CompositePersonnel find(String id) {
		 CompositePersonnel obj = null;
		 try {
			   String sql = "select * from Groupes where groupeid = (?)";
			   int groupeid = Integer.parseInt(id);
			   PreparedStatement select = connect.prepareStatement(sql);
			   select.setInt(1, groupeid);
			   select.execute();
			   obj = new CompositePersonnel(select.getResultSet().getInt(1));
			   PersonnelDaoSGBD pd = new PersonnelDaoSGBD(); 
			   ArrayList<Personnel> personnels = pd.getPersonnels(obj.getID());
			   
			   for(Personnel p : personnels ) {
				   obj.add(p);
			   }
			   
		} catch (SQLException e) {
			   e.printStackTrace();
		}
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
