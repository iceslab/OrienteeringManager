package pl.edu.repository.routestep;

import pl.edu.model.competitor.Competitor;
import pl.edu.model.routestep.RouteStep;
import pl.edu.repository.IRepository;

public interface IRouteStepRepository extends IRepository<RouteStep, Long> {

    RouteSteps findAll();
}
