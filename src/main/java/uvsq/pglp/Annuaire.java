package uvsq.pglp;

import java.io.Serializable;



public class Annuaire implements Serializable {

  private static final long serialVersionUID = 1L;
  private static Annuaire ANNUAIRE;
  private CompositePersonnel  head;

  private Annuaire() {
    head = new CompositePersonnel(1);
  }

  /**
   * Public constructor for singleton.
   * @return Static reference to the object   
   **/
  public static Annuaire getInstance() {
    if (ANNUAIRE == null) {
      ANNUAIRE = new Annuaire();
    }
    return ANNUAIRE;
  }

  public void addGroupe(CompositePersonnel  c) {
    head.add(c);
  }

  public void removeGroupe(CompositePersonnel  c) {
    head.remove(c);
  }

  public GroupeIteration getIterator() {
    return ANNUAIRE.head.groupeIteration();
  }

  
}
