package uvsq.pglp;

public class DaoFactorySerialization implements AbstractFactoryDao{

	@Override
	public Dao<Annuaire> createAnnuaireDao() {
		// TODO Auto-generated method stub
		return new AnnuaireDaoSerialization();
	}

	@Override
	public Dao<CompositePersonnel> createCompositePersonnelDao() {
		// TODO Auto-generated method stub
		return new GroupeDaoSerialization();
	}

	@Override
	public Dao<Personnel> createPersonnelDao() {
		// TODO Auto-generated method stub
		return new PersonnelDaoSerialization();
	}

}
