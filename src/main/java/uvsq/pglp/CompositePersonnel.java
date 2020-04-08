package uvsq.pglp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


/**
* CompositePersonnel class.
*/
public class CompositePersonnel  extends TypePersonnel implements
       Iterable<TypePersonnel>, Serializable {

  private static final long serialVersionUID = 1L;
  /**
  * Groupe de personnel.
  */
  private List<TypePersonnel> personnels;
  /**
  * id.
  */
  private int id;
  /**
  * add personnel to the groupe.
  * @param personnel personnel to be added.
  */

  public void add(final TypePersonnel personnel) {
    this.personnels.add(personnel);
  }
  /**
  * Constructor.
  * @param idGroupe id du groupe.
  */

  public CompositePersonnel(final int idGroupe) { 
    this.personnels = new ArrayList<TypePersonnel>();
    this.id = idGroupe;
  }
  /**
  * remove personnel from groupe.
  * @param personnel personnel to be removed.
  */

  public void remove(final TypePersonnel personnel) {
    this.personnels.remove(personnel);
  }
  /**
  * return all personnel.
  * @return les les personnel.
  */

  public List<TypePersonnel> getAllPersonnel() {
    return this.personnels;
  }
  /** check if object is groupe or not.
  *  @return groupe.
  */

  @Override
  public boolean isGroupe() {
    return true;
  }

  /** iterator.
  *  @return iterator.
  */

  public Iterator<TypePersonnel> iterator() { 
    return personnels.listIterator();
  }

  /** getter for groupe iteration.
  *  @return groupe iteration.
  */

  public GroupeIteration groupeIteration() {
    return new GroupeIteration(this);
  }
  /** getter for hierachical iteration.
  *  @return groupe.
  */

  public HierachicalIteration hierachical() {
    return new HierachicalIteration(this);
  }

  /** getter for id.
  *  @return string representation of the object.
  */
  public int getID() {
    return this.id;
  }

  /* (non-Javadoc)
 * @see java.lang.Object#hashCode()
 */
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof CompositePersonnel)) {
      CompositePersonnel other = (CompositePersonnel) obj;
      return (id == other.id);
    }
    return false;
  }
}
