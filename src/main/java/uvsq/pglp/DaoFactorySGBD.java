package uvsq.pglp;

public class DaoFactorySGBD implements AbstractFactoryDao{

	@Override
	public Dao<Annuaire> createAnnuaireDao() {
		// TODO Auto-generated method stub
		return new AnnuaireDaoSGBD();
	}

	@Override
	public Dao<Personnel> createPersonnelDao() {
		// TODO Auto-generated method stub
		return new PersonnelDaoSGBD();
	}

	@Override
	public Dao<CompositePersonnel> createCompositePersonnelDao() {
		// TODO Auto-generated method stub
		return new GroupeDaoSGBD();
	}

}
