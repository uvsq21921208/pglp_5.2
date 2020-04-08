package uvsq.pglp;

import java.util.Stack;
/**
* Hierachical iteration class.
*/

public class HierachicalIteration extends TypePersonnelIterator {
  /** public constructor.
  *  @param compositePersonnel param.
  */
  public HierachicalIteration(final CompositePersonnel compositePersonnel) {
    super(compositePersonnel, new Stack<TypePersonnel>());
  }

  /** get last item.
  *  @return les personnel.
  */

  @Override
  protected TypePersonnel getCollectionItems() {
    return ((Stack<TypePersonnel>) getPersonnelCollection()).pop();
  }

}
