package uvsq.pglp;

public interface AbstractFactoryDao {

	  Dao<Annuaire> createAnnuaireDao();

	  Dao<Personnel> createPersonnelDao();

	  Dao<CompositePersonnel> createCompositePersonnelDao();
}
