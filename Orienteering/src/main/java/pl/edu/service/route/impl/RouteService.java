package pl.edu.service.route.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.model.route.Route;
import pl.edu.repository.route.IRouteRepository;
import pl.edu.repository.route.Routes;
import pl.edu.service.route.IRouteService;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RouteService implements IRouteService {

	private static final long serialVersionUID = -7007444203780713873L;

	@Autowired
	private IRouteRepository routeRepository;

	@Override
	public void delete(Route route) {
		routeRepository.delete(route);
	}

	@Override
	public void saveOrUpdate(Route route) {
		routeRepository.saveOrUpdate(route);
	}

	@Override
	public void save(Route route) {
		routeRepository.save(route);
	}

	@Override
	public boolean saveIfNew(Route route) {
		return false;
	}

	@Override
	public List<Route> list(Routes routes) {
		return routeRepository.findAll().merge(routes).list();
	}

	@Override
	public long count(Routes routes) {
		return routeRepository.findAll().merge(routes).count();
	}

	@Override
	public Route uniqueObject(Routes routes) {
		return routeRepository.findAll().merge(routes).uniqueObject();
	}

	@Override
	public void update(Route route) {
		routeRepository.update(route);
	}

	@Override
	public boolean exists(Routes routes) {
		Route route = routeRepository.findAll().merge(routes).uniqueObject();
		routeRepository.evict(route);
		return route != null;
	}
}
