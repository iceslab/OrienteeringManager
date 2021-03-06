package pl.edu.repository.user;

import org.springframework.stereotype.Repository;
import pl.edu.model.user.User;
import pl.edu.repository.StandardDatabaseRepository;
import pl.edu.model.BaseEntity;

@Repository
public class DatabaseUserRepository extends StandardDatabaseRepository<User, Long> implements IUserRepository{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522593563237649316L;

	@Override
	public Users findAll() {
		return new CriteriaUsers(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return User.class;
	}
}
