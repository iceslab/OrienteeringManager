package pl.edu.service.routestep;

import pl.edu.model.competitor.Competitor;
import pl.edu.model.routestep.RouteStep;
import pl.edu.repository.competitor.Competitors;
import pl.edu.repository.routestep.RouteSteps;
import pl.edu.service.IService;

import java.util.List;

public interface IRouteStepService extends IService {

	void delete(RouteStep routeStep);

	void update(RouteStep routeStep);
	
	boolean exists(RouteSteps routeSteps);
	
	void saveOrUpdate(RouteStep routeStep);
	
	void save(RouteStep routeStep);
	
	boolean saveIfNew(RouteStep routeStep);

	List<RouteStep> list(RouteSteps routeSteps);

	long count(RouteSteps routeSteps);

	RouteStep uniqueObject(RouteSteps routeSteps);
}
