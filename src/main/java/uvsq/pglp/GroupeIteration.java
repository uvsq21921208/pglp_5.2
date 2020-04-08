package uvsq.pglp;

import java.util.ArrayDeque;

/**
* groupe iteration class.
*
*/
public class GroupeIteration extends TypePersonnelIterator { 
  /** public constructor.
  *  @param compositePersonnel param.
  */
  public GroupeIteration(final CompositePersonnel compositePersonnel) { 
    super(compositePersonnel, new ArrayDeque<TypePersonnel>());
  }

  /** return return last item.
  *  @return les personnel.
  */
  @Override
  protected TypePersonnel getCollectionItems() {
    return ((ArrayDeque<TypePersonnel>) getPersonnelCollection()).remove();
  }

}
