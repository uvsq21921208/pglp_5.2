package uvsq.pglp;

public class DaoFactorySgbd implements AbstractFactoryDao {

  @Override
  public Dao<Annuaire> createAnnuaireDao() {

    return new AnnuaireDaoSgbd();
  }

  @Override
  public Dao<Personnel> createPersonnelDao() {

    return new PersonnelDaoSgbd();
  }

  @Override
  public Dao<CompositePersonnel> createCompositePersonnelDao() {

    return new GroupeDaoSgbd();
  }

}