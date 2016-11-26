package pl.edu.service.route;

import pl.edu.model.competitor.Competitor;
import pl.edu.model.route.Route;
import pl.edu.repository.competitor.Competitors;
import pl.edu.repository.route.Routes;
import pl.edu.service.IService;

import java.util.List;

public interface IRouteService extends IService {

	void delete(Route route);

	void update(Route route);
	
	boolean exists(Routes routes);
	
	void saveOrUpdate(Route route);
	
	void save(Route route);
	
	boolean saveIfNew(Route route);

	List<Route> list(Routes routes);

	long count(Routes routes);

	Route uniqueObject(Routes routes);
}
