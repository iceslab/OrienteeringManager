package pl.edu.repository.route;

import org.springframework.stereotype.Repository;
import pl.edu.model.BaseEntity;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.route.Route;
import pl.edu.repository.StandardDatabaseRepository;

@Repository
public class DatabaseRouteRepository extends StandardDatabaseRepository<Route, Long> implements IRouteRepository {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7522593563237649316L;

	@Override
	public Routes findAll() {
		return new CriteriaRoutes(createCriteria(), createCriteria());
	}

	@Override
	public Class<? extends BaseEntity<Long>> getEntityClass() {
		return Route.class;
	}
}
