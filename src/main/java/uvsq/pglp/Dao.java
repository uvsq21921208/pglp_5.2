package uvsq.pglp;

public abstract class Dao<T> {
    
  public abstract T create(T obj);

  public abstract T find(String id);

  public abstract T update(T obj);

  public abstract void delete(T obj);
}  
