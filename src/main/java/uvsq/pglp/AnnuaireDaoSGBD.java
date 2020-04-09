package uvsq.pglp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnnuaireDaoSGBD extends Dao<Annuaire>{
	 Statement statement;
	@Override
	public Annuaire create(Annuaire obj) {
		// TODO Auto-generated method stub
		 try {
			   String sql = "CREATE TABLE Groupes(groupeid INTEGER primary key not null)";
			   statement = connect.createStatement();
			   statement.execute(sql);
			   sql = "CREATE TABLE Personnels(nom varchar(20) PRIMARY KEY NOT NULL, prenom  varchar(20), fonction varchar(20),"
				   		+ "groupeid integer references Groupes(groupeid))";
				   statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return obj;
	}

	@Override
	public Annuaire find(String id) {
		return null;
		
	}

	@Override
	public Annuaire update(Annuaire obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Annuaire obj) {
		String sql = "drop table personnels";
		try {
			statement = connect.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sql = "drop table groupes";
		try {
			statement.execute(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
