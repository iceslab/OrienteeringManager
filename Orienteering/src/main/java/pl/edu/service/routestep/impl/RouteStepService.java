package pl.edu.service.routestep.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.model.routestep.RouteStep;
import pl.edu.repository.routestep.IRouteStepRepository;
import pl.edu.repository.routestep.RouteSteps;
import pl.edu.service.routestep.IRouteStepService;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RouteStepService implements IRouteStepService {

	private static final long serialVersionUID = -7007444203780713873L;

	@Autowired
	private IRouteStepRepository routeStepRepository;

	@Override
	public void delete(RouteStep routeStep) {
		routeStepRepository.delete(routeStep);
	}

	@Override
	public void saveOrUpdate(RouteStep routeStep) {
		routeStepRepository.saveOrUpdate(routeStep);
	}

	@Override
	public void save(RouteStep routeStep) {
		routeStepRepository.save(routeStep);
	}

	@Override
	public boolean saveIfNew(RouteStep routeStep) {
		return false;
	}

	@Override
	public List<RouteStep> list(RouteSteps routeSteps) {
		return routeStepRepository.findAll().merge(routeSteps).list();
	}

	@Override
	public long count(RouteSteps routeSteps) {
		return routeStepRepository.findAll().merge(routeSteps).count();
	}

	@Override
	public RouteStep uniqueObject(RouteSteps routeSteps) {
		return routeStepRepository.findAll().merge(routeSteps).uniqueObject();
	}

	@Override
	public void update(RouteStep routeStep) {
		routeStepRepository.update(routeStep);
	}

	@Override
	public boolean exists(RouteSteps routeSteps) {
		RouteStep routeStep = routeStepRepository.findAll().merge(routeSteps).uniqueObject();
		routeStepRepository.evict(routeStep);
		return routeStep != null;
	}
}
