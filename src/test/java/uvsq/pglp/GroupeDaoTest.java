package uvsq.pglp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import uvsq.pglp.CompositePersonnel;
import uvsq.pglp.GroupeDao;
import uvsq.pglp.Personnel;

public class GroupeDaoTest {
	
	
	Dao<CompositePersonnel> dao;
	@Test
	public void createTest(){
		File tmpDir = new File("Groupe1");
		tmpDir.delete();
		dao = new GroupeDao();
		Personnel p1 = new Personnel.PersonelBuilder("Djekhaba", "Mouttie", "CS").build();
		CompositePersonnel g1 = new CompositePersonnel(1); 
		assertFalse(tmpDir.exists());
		g1.add(p1);
		dao.create(g1);
		assertTrue(tmpDir.exists());
		
		
	}
    
	@Test
	public void updateTest(){
		dao = new GroupeDao();
		createTest();
		File tmpDir = new File("Groupe1");
		CompositePersonnel g1 = dao.find("Groupe1");
		Personnel p1 = new Personnel.PersonelBuilder("Djekhaba", "Mouttie", "CS").build();
		assertEquals(((Personnel) g1.getAllPersonnel().get(0)).getNom(),p1.getNom());
		g1.remove(p1);
		
	
		p1 = new Personnel.PersonelBuilder("Djekhaba", "Mouttie", "Design").build();
	
		g1.add(p1);

		dao.update(g1);
		g1 = dao.find("Groupe1");
		assertEquals(((Personnel) g1.getAllPersonnel().get(0)).getFonction(),"Design");
		
		
		
	}
    
	@Test
	public void deleteTest(){
		File tmpDir = new File("Groupe1");
		dao = new GroupeDao();
		CompositePersonnel p1 = new CompositePersonnel(1);
	    dao.create(p1);
		assertTrue(tmpDir.exists());
		dao.delete(p1);
		assertFalse(tmpDir.exists());
	}
	@Test
	public void findTest(){
		
		createTest();
		CompositePersonnel g1 = dao.find("Groupe1");
		assertEquals(g1.getID(), 1);
	}
    
    
	
}
