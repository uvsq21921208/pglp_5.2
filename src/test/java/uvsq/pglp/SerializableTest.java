package uvsq.pglp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uvsq.pglp.Annuaire;
import uvsq.pglp.CompositePersonnel;
import uvsq.pglp.Personnel;
import uvsq.pglp.TypePersonnel;
import uvsq.pglp.TypePersonnelIterator;

public class SerializableTest {

	Personnel p1;
	Personnel p2;
	Personnel p3;
	Annuaire a;
	
	@Before
	
	public void setUp() {
		p1 = new Personnel.PersonelBuilder("Mouttie", "Djekhaba", "CS").build();
		p2= new Personnel.PersonelBuilder("Jack", "Black", "Design").build();
		p3 = new Personnel.PersonelBuilder("Tom", "Petty", "Audio").build();
	    a = Annuaire.getInstance();
	    CompositePersonnel g1 = new CompositePersonnel(1);
	    CompositePersonnel g2 = new CompositePersonnel(2);
		
		g1.add(p1);
		g1.add(p2);
		g2.add(p3);
		a.addGroupe(g1);
		a.addGroupe(g2);

	}
	
	@Test
	public void saveObject(){
		
	    try {
	         FileOutputStream fileOut =
	         new FileOutputStream("data");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(a);
	         out.close();
	         fileOut.close();
	         
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	    
	    
	    
	}
	@Test
	public void readObject() throws ClassNotFoundException {
		 try {
	         FileInputStream fileIn = new FileInputStream("data");
	       
	         ObjectInputStream in = new ObjectInputStream(fileIn);
             Annuaire b = (Annuaire) in.readObject();
             TypePersonnelIterator iter1 =  a.getIterator();
     	     TypePersonnelIterator iter2 =  b.getIterator();
            
            while(iter1.hasNext() && iter2.hasNext()) {
            	  TypePersonnel g1 = iter1.next();
            	  TypePersonnel g2 = iter2.next();
            	  if (g1.isGroupe() && g2.isGroupe()) {
            		  assertEquals(((CompositePersonnel) g1).getID(), ((CompositePersonnel) g2).getID()); 
            		  List<TypePersonnel> k1 = ((CompositePersonnel) g1).getAllPersonnel();
            		  List<TypePersonnel> k2 = ((CompositePersonnel) g2).getAllPersonnel();
                      assertEquals(k1,k2);
                  
            	  }
            	  
            }
          
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	    
	}
	
	
}
