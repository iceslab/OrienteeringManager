package pl.edu.repository.routestep;

import org.springframework.stereotype.Repository;
import pl.edu.model.BaseEntity;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.routestep.RouteStep;
import pl.edu.repository.StandardDatabaseRepository;

@Repository
public class DatabaseRouteStepRepository extends StandardDatabaseRepository<RouteStep, Long> implements IRouteStepRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522593563237649316L;

	@Override
	public RouteSteps findAll() {
		return new CriteriaRouteSteps(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return RouteStep.class;
	}
}
