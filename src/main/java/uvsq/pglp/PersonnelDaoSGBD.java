package uvsq.pglp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PersonnelDaoSGBD extends Dao<Personnel>{

	@Override
	public Personnel create(Personnel obj) {
		this.connect();
		int i = - 1;
        try {
		   PreparedStatement insert = this.connect.prepareStatement("Insert into Personnels(nom, prenom, fonction,groupeid)"
					+ " values (?,?,?,?)");
		   insert.setString(1, obj.getNom());
		   insert.setString(2, obj.getPrenom());
		   insert.setString(3, obj.getFonction());
		   insert.setInt(4, obj.getGroupeId());
		   i = insert.executeUpdate();
		   insert.close();
		   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	public Personnel find(String id) {
		Personnel p = null;
		this.connect();
		try {
			PreparedStatement select = this.connect.prepareStatement("select * from personnels"
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.disconnect();
		return p;
		
	}

	@Override
	public Personnel update(Personnel obj) {
		this.connect();
		try {
			PreparedStatement update = this.connect.prepareStatement("update Personnels"
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.disconnect();
		return obj;
	}

	@Override
	public void delete(Personnel obj) {
		this.connect();
		try {
			PreparedStatement delete = this.connect.prepareStatement("delete from Personnels "+
					 "where nom = (?)");
			delete.setString(1, obj.getNom());
			delete.execute();
			delete.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	public ArrayList<Personnel> getPersonnels(int groupeId){

		ArrayList<Personnel> personnels = new ArrayList<Personnel>();
		this.connect();
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
		
		//this.disconnect();
		return personnels;
	}
}
