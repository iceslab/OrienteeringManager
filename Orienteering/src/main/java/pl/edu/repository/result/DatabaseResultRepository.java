package pl.edu.repository.result;

import org.springframework.stereotype.Repository;
import pl.edu.model.BaseEntity;
import pl.edu.model.result.Result;
import pl.edu.repository.StandardDatabaseRepository;

@Repository
public class DatabaseResultRepository extends StandardDatabaseRepository<Result, Long> implements IResultRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522593563237649316L;

	@Override
	public Results findAll() {
		return new CriteriaResults(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return Result.class;
	}
}
