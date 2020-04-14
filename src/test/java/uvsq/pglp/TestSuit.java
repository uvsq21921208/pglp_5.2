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

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import uvsq.pglp.Annuaire;

import uvsq.pglp.CompositePersonnel;
import uvsq.pglp.Personnel;

public class TestSuit {
     Statement statement;
    
    @Before
	public  void setUp(){
		   
		   Annuaire a = Annuaire.getInstance();
		   DaoFactorySGBD daoFac = new DaoFactorySGBD();
		   Dao<Annuaire> dao = daoFac.createAnnuaireDao();
		   try {
			String sql ="";
			dao.connect();
			statement = dao.connect.createStatement();
			ResultSet result;
		  
			result = dao.connect.getMetaData().getTables(null, null, "PERSONNELS", null);
			while(result.next()) {
				if (result.getString("TABLE_NAME").equals("PERSONNELS")) {
					
					sql = "Drop table Personnels";  
					statement.execute(sql);
				}
				
			}
			result = dao.connect.getMetaData().getTables(null, null, "GROUPES", null);
			
			while(result.next()) {
				if (result.getString("TABLE_NAME").equals("GROUPES")) {
			
					sql = "Drop table Groupes";  
					statement.execute(sql);
				}
				
			}
		  
			
			
			dao.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
	}

    @Test
	public void createAnnuaireTest() throws SQLException{
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
    public void deleteAnnuaireTest() throws SQLException {
    	Annuaire a = Annuaire.getInstance();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Annuaire> dao = daoFac.createAnnuaireDao();
	    dao.connect();
		dao.create(a);
        dao.delete(a);
        ResultSet result;
        result = dao.connect.getMetaData().getTables(null, null, "Personnels", null);
    	while (result.next()) {
			assertNull(result.getString("TABLE_NAME"));
		}
		result = dao.connect.getMetaData().getTables(null, null, "Groupes", null);
		while (result.next()) {
			assertNull(result.getString("TABLE_NAME"));
		}
    }
    @Test
	public void createGroupeTest() throws SQLException {
		
		createAnnuaireTest();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Personnel> dao = daoFac.createPersonnelDao();
		Dao<CompositePersonnel> daoG = daoFac.createCompositePersonnelDao();
		Personnel p = new Personnel.PersonelBuilder("Eren","Yeager", "CS").groupeId(1).build();
		CompositePersonnel comp = new CompositePersonnel(1);
		comp.add(p);
		assertNotNull(daoG.create(comp));
		//dao.create(p);
		
		
	}
	
    @Test
	public void createPersonnelTest() throws SQLException {
		createAnnuaireTest();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Personnel> dao = daoFac.createPersonnelDao();
		Dao<CompositePersonnel> daoG = daoFac.createCompositePersonnelDao();
		Personnel p = new Personnel.PersonelBuilder("Jack","Black", "CS").groupeId(3).build();
		CompositePersonnel comp = new CompositePersonnel(3);
		daoG.create(comp);
		assertNotNull(dao.create(p));
	
	}
	@Test
	public void updatePersonnelTest() throws SQLException {
		createAnnuaireTest();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Personnel> dao = daoFac.createPersonnelDao();
		Dao<CompositePersonnel> daoG = daoFac.createCompositePersonnelDao();
		Personnel p = new Personnel.PersonelBuilder("Djekhaba","Mouttie", "CS").groupeId(1).build();
		CompositePersonnel comp = new CompositePersonnel(1);
		comp.add(p);
		daoG.create(comp);
		dao.create(p);
		p = new Personnel.PersonelBuilder("Djekhaba","Mouttie", "Design").groupeId(1).build();
		
		dao.update(p);
		Personnel result = dao.find("Djekhaba");
		assertEquals(result.getFonction(), "Design");
	}
	
    @Test
	public void findPersonnelTest() throws SQLException {
    	
		createAnnuaireTest();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Personnel> dao = daoFac.createPersonnelDao();
		Dao<CompositePersonnel> daoG = daoFac.createCompositePersonnelDao();
		Personnel p = new Personnel.PersonelBuilder("Djekhaba","Mouttie", "CS").groupeId(1).build();
		CompositePersonnel comp = new CompositePersonnel(1);
		comp.add(p);
		daoG.create(comp);
		dao.create(p);
		Personnel result;
		//in case the requested personnel doesn't exist.
		result = dao.find("zaeazeaze");
		assertNull(result);
		result = dao.find("Djekhaba");
		assertEquals(result.getNom(), p.getNom());
		
		
	}
    
    @Test
	public void findGroupeTest() throws SQLException {
		createAnnuaireTest();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Personnel> dao = daoFac.createPersonnelDao();
		Dao<CompositePersonnel> daoG = daoFac.createCompositePersonnelDao();
		Personnel p = new Personnel.PersonelBuilder("Ilham","Armin", "CS").groupeId(2).build();
		CompositePersonnel comp = new CompositePersonnel(2);
		comp.add(p);
		daoG.create(comp);
		dao.create(p);
		//in case the requested groupe doesn't exist.
		CompositePersonnel result = daoG.find("5487");
		assertNull(result);
		result = daoG.find("2");
		assertEquals(((Personnel) result.getAllPersonnel().get(0)).getNom(), p.getNom());
		
	}
    @Test
    public void deleteGroupeTest() throws SQLException {
    	createAnnuaireTest();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Personnel> dao = daoFac.createPersonnelDao();
		Dao<CompositePersonnel> daoG = daoFac.createCompositePersonnelDao();
		Personnel p = new Personnel.PersonelBuilder("Ya","Armin", "CS").groupeId(5).build();
		CompositePersonnel comp = new CompositePersonnel(5);
		comp.add(p);
		daoG.create(comp);
		dao.create(p);
		assertNotNull(daoG.find("5"));
		daoG.delete(comp);
		assertNull(daoG.find("5"));
    }
    @Test 
    public void deletePersonnelTest() throws SQLException {
    	createAnnuaireTest();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Personnel> dao = daoFac.createPersonnelDao();
		Dao<CompositePersonnel> daoG = daoFac.createCompositePersonnelDao();
		Personnel p = new Personnel.PersonelBuilder("Orjan","Black", "CS").groupeId(6).build();
		CompositePersonnel comp = new CompositePersonnel(6);
		daoG.create(comp);
		dao.create(p);
		assertNotNull(dao.find("Orjan"));
		dao.delete(p);
		assertNull(dao.find("Orjan"));
    }
    @Test
    public void updateGroupeTest() throws SQLException {
		createAnnuaireTest();
		DaoFactorySGBD daoFac = new DaoFactorySGBD();
		Dao<Personnel> dao = daoFac.createPersonnelDao();
		Dao<CompositePersonnel> daoG = daoFac.createCompositePersonnelDao();
		Personnel p = new Personnel.PersonelBuilder("Jack","john", "CS").groupeId(10).build();
		CompositePersonnel comp = new CompositePersonnel(10);
		comp.add(p);
		daoG.create(comp);
		dao.create(p);
		CompositePersonnel result = daoG.find("10");
		assertEquals(((Personnel) result.getAllPersonnel().get(0)).getNom(), p.getNom());
		p = new Personnel.PersonelBuilder("Jack","gates", "Design").groupeId(10).build();
		comp = new CompositePersonnel(10);
		comp.add(p);
		daoG.update(comp);
		result = daoG.find("10");
		assertEquals(((Personnel) result.getAllPersonnel().get(0)).getFonction(), p.getFonction());
		
    }
    
    
}
