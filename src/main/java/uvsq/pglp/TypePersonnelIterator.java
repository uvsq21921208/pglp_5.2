package uvsq.pglp;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
/** Type personnel iterator.
* @author Mouttie
* 
*/

public abstract class TypePersonnelIterator implements Iterator<TypePersonnel> {
  /**
  * collection des personnels.
  */
  private Collection<TypePersonnel> personnelCollection;

  /**
  * check if it has next item or not.
  * @return has next ou pas.
  */

  public boolean hasNext() {
    return !getPersonnelCollection().isEmpty();
  }
  /**
  * root.
  */

  private CompositePersonnel root;
  /**
  * TypePersonnelIterator.
  * @param compositePersonnel composite personnel.
  * @param collection collection.
  */

  public TypePersonnelIterator(final CompositePersonnel compositePersonnel,
         final Collection<TypePersonnel> collection) {
    this.root = compositePersonnel;
    this.setPersonnelCollection(collection);
    this.getPersonnelCollection().addAll(root.getAllPersonnel());
  }

  /**
  * setter for personnel colleciton.
  * @param collection collection.
  */
  private void setPersonnelCollection(final Collection<TypePersonnel>
           collection) {
    this.personnelCollection = collection;
  }

  /**
   * returns next element.
  * @return next element.
  */
  public TypePersonnel next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    TypePersonnel typePersonnel = getCollectionItems();
    if (typePersonnel.isGroupe()) {
      getPersonnelCollection().addAll(((CompositePersonnel) typePersonnel).getAllPersonnel());
    }
    return typePersonnel;
  }
  /**
  * get last item.
  * @return collection.
  */

  protected abstract TypePersonnel getCollectionItems();

  /** 
  * getter for root.
  * @return root.
  */
  public TypePersonnel getRoot() {
    return this.root;
  }
  /**
  * get personnel collection.
  * @return personnel collection.
  */

  public Collection<TypePersonnel> getPersonnelCollection() {
    return personnelCollection;
  }
}
