package pl.edu.repository.punch;

import org.springframework.stereotype.Repository;
import pl.edu.model.BaseEntity;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.punch.Punch;
import pl.edu.repository.StandardDatabaseRepository;

@Repository
public class DatabasePunchRepository extends StandardDatabaseRepository<Punch, Long> implements IPunchRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522593563237649316L;

	@Override
	public Punches findAll() {
		return new CriteriaPunches(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return Punch.class;
	}
}
