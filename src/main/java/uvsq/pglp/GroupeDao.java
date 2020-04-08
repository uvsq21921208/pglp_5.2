package uvsq.pglp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GroupeDao extends Dao<CompositePersonnel> {

  @Override
  public CompositePersonnel create(CompositePersonnel obj) {
    try (ObjectOutputStream out = new ObjectOutputStream(new 
                     FileOutputStream("Groupe" + obj.getID()))) {
      out.writeObject(obj);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return obj;
  }

  @Override
  public CompositePersonnel find(String id) {
    CompositePersonnel groupe = null;
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(id))) {
      groupe = (CompositePersonnel) in.readObject();
    } catch (ClassNotFoundException  ioe) {
      ioe.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return groupe;
  }

  @Override
  public CompositePersonnel update(CompositePersonnel obj) {
    String file = "Groupe" + obj.getID();
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, false))) {
      out.writeObject(obj);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return obj;
  }

  @Override
  public void delete(CompositePersonnel obj) {
    File file = new File("Groupe" + obj.getID());
    boolean deleted = file.delete();
    if (deleted) {
      System.out.println("deleted");
    } else {
      System.out.println("not deleted");
    }
  }
}
