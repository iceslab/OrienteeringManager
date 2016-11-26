package pl.edu.repository.route;

import pl.edu.model.route.Route;
import pl.edu.repository.IRepository;

public interface IRouteRepository extends IRepository<Route, Long> {

    Routes findAll();
}
