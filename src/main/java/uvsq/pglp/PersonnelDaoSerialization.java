package uvsq.pglp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PersonnelDaoSerialization extends Dao<Personnel> {

  @Override
  public Personnel create(Personnel obj) {
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(obj.getNom()))) {
      out.writeObject(obj);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return obj;
  }

  @Override
  public Personnel find(String id) {
    Personnel personnel = null;
    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(id))) {
      personnel = (Personnel) in.readObject();
    } catch (ClassNotFoundException  ioe) {
      ioe.printStackTrace();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return personnel;
  }

  @Override
  public Personnel update(Personnel obj) {
    String file = obj.getNom();
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file, false))) {
      out.writeObject(obj);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
    return obj;
  }

  @Override
  public void delete(Personnel obj) {

    File file = new File(obj.getNom());
    boolean deleted = file.delete();
    if (deleted) {
      System.out.println("deleted");
    } else {
      System.out.println("not deleted");
    }
  }
}
