package uvsq.pglp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AnnuaireDaoSerialization extends Dao<Annuaire> {


  @Override
  public Annuaire create(Annuaire obj) {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Annuaire"))) {
      out.writeObject(obj);
    } catch (IOException ioe) { 
      ioe.printStackTrace();
    }
    return obj;
  }

  @Override
  public Annuaire find(String id) {
    Annuaire annuaire = null;
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(id))) {
      annuaire = (Annuaire) in.readObject();
    
    } catch (ClassNotFoundException  ioe) {
      ioe.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return annuaire;
  }

  @Override
  public Annuaire update(Annuaire obj) {
    String file = "Annuaire";
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, false))) {
      out.writeObject(obj);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return obj;
  }

  @Override
  public void delete(Annuaire obj) {
    File file = new File("Annuaire");
    boolean deleted = file.delete();
    if (deleted) {
      System.out.println("deleted");
    } else {
      System.out.println("not deleted");
    }
  }
}

