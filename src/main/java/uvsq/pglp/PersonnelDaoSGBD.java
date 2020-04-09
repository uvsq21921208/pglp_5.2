package uvsq.pglp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonnelDaoSGBD extends Dao<Personnel>{

	@Override
	public Personnel create(Personnel obj) {
        try {
		   PreparedStatement insert = this.connect.prepareStatement("Insert into Personnels(nom, prenom, fonction,groupeid)"
					+ " values (?,?,?,?)");
		   insert.setString(1, obj.getNom());
		   insert.setString(2, obj.getPrenom());
		   insert.setString(3, obj.getFonction());
		   insert.setInt(4, obj.getGroupeId());
		   insert.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Personnel find(String id) {
		Personnel p = null;
		try {
			PreparedStatement select = this.connect.prepareStatement("select * from personnel"
					+ " where nom = (?)");
			select.setString(1, id);
			select.execute();
			String nom = select.getResultSet().getString("nom");
			String prenom = select.getResultSet().getString("prenom");
			String fonction = select.getResultSet().getString("Fonction");
			int groupeid = select.getResultSet().getInt("groupeid");
			p = new Personnel.PersonelBuilder(nom, prenom, fonction).groupeId(groupeid).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
		
	}

	@Override
	public Personnel update(Personnel obj) {
		try {
			PreparedStatement update = this.connect.prepareStatement("update table Personnels"
					+ " set nom = (?), prenom = (?), fonction = (?), groupeid = (?)"
					+ " where nom = (?) ");
			update.setString(1, obj.getNom());
			update.setString(2, obj.getPrenom());
			update.setString(3, obj.getFonction());
			update.setInt(4, obj.getGroupeId());
			update.setString(5, obj.getNom());
			update.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public void delete(Personnel obj) {
		try {
			PreparedStatement delete = this.connect.prepareStatement("delete from Personnels "+
					 " nom = (?)");
			delete.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<Personnel> getPersonnels(int groupeId){
		ArrayList<Personnel> personnels = new ArrayList<Personnel>();
		try {
			PreparedStatement select = this.connect.prepareStatement("Select * from Personnels where groupeid = (?)");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return personnels;
	}
}
