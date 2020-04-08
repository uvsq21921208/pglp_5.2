package uvsq.pglp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import uvsq.pglp.Annuaire;
import uvsq.pglp.AnnuaireDao;
import uvsq.pglp.CompositePersonnel;
import uvsq.pglp.Personnel;
import uvsq.pglp.TypePersonnel;
import uvsq.pglp.TypePersonnelIterator;

public class AnnuaireDaoTest {

	
	

	Dao<Annuaire> dao;
	
	@Test
	public void createTest(){
		Annuaire a = Annuaire.getInstance();
		File tmpDir = new File("Annuaire");
		tmpDir.delete();
		dao = new AnnuaireDao();
		assertFalse(tmpDir.exists());
		dao.create(a);
		assertTrue(tmpDir.exists());
	}
    
	@Test
	public void updateTest(){
		File tmpDir = new File("Annuaire");
		tmpDir.delete();
		dao = new AnnuaireDao();
		Annuaire a = Annuaire.getInstance();
		Personnel p1 = new Personnel.PersonelBuilder("Djekhaba", "Mouttie", "CS").build();
		CompositePersonnel g1 = new CompositePersonnel(1);
		g1.add(p1);
		a.addGroupe(g1);
	    dao.create(a);
		p1 = new Personnel.PersonelBuilder("Djekhaba", "Mouttie", "Design").build();
		g1 = new CompositePersonnel(1);
		a.removeGroupe(g1);
		g1.add(p1);
		a.addGroupe(g1);
		TypePersonnelIterator iter = a.getIterator();
        TypePersonnel t = iter.next();
        assertEquals(((Personnel) ((CompositePersonnel) t).getAllPersonnel().get(0)).getFonction(), "Design");
     
	}	
	
	@Test
	public void deleteTest(){
		Annuaire a = Annuaire.getInstance();
		File tmpDir = new File("Annuaire");
		dao = new AnnuaireDao();
		dao.create(a);
		assertTrue(tmpDir.exists());
		dao.delete(a);
		assertFalse(tmpDir.exists());
		
	}

	@Test
	public void findTest(){
		createTest();
		Annuaire c = dao.find("Annuaire");
		assertNotNull(c);
		
	}
    
	
	
}
