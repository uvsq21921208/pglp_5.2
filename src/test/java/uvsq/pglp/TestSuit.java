package uvsq.pglp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import uvsq.pglp.Annuaire;

import uvsq.pglp.CompositePersonnel;
import uvsq.pglp.Personnel;
import uvsq.pglp.TypePersonnel;
import uvsq.pglp.TypePersonnelIterator;

public class TestSuit {
    Statement statement;
    
	@Before
	public void setUp(){
		   Annuaire a = Annuaire.getInstance();
		   DaoFactorySGBD daoFac = new DaoFactorySGBD();
		   Dao<Annuaire> dao = daoFac.createAnnuaireDao();
		   try {
			String sql = "Drop table Personnels";   
			statement = dao.connect().createStatement();
			statement.execute(sql);
			sql = "Drop table Groupes";
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}

	@Test
	public void CreateAnnuaireTest() throws SQLException{
		Annuaire a = Annuaire.getInstance();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Annuaire> dao = daoFac.createAnnuaireDao();
	    dao.connect();
		dao.create(a);
		ResultSet result = dao.connect.getMetaData().getTables(null, null, "Personnels", null);
		result = dao.connect.getMetaData().getTables(null, null, "Personnels", null);
		while (result.next()) {
			assertEquals(result.getString("TABLE_NAME"), "Personnels");
		}
		result = dao.connect.getMetaData().getTables(null, null, "Groupes", null);
		while (result.next()) {
			assertEquals(result.getString("TABLE_NAME"), "Groupes");
		}
	}
	@Test
	public void CreateGroupeTest() {
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<CompositePersonnel> dao = daoFac.createCompositePersonnelDao();
		CompositePersonnel comp = new CompositePersonnel(1);
	}
	
	
}
