package uvsq.pglp;

public class DaoFactorySerialization implements AbstractFactoryDao {

  @Override
  public Dao<Annuaire> createAnnuaireDao() {
    return new AnnuaireDaoSerialization();
  }

  @Override
  public Dao<CompositePersonnel> createCompositePersonnelDao() {
    return new GroupeDaoSerialization();
  }

  @Override
  public Dao<Personnel> createPersonnelDao() {
    return new PersonnelDaoSerialization();
  }

}