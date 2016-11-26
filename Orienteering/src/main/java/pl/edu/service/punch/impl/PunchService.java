package pl.edu.service.punch.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.model.punch.Punch;
import pl.edu.repository.punch.IPunchRepository;
import pl.edu.repository.punch.Punches;
import pl.edu.service.punch.IPunchService;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PunchService implements IPunchService {

	private static final long serialVersionUID = -7007444203780713873L;

	@Autowired
	private IPunchRepository punchRepository;

	@Override
	public void delete(Punch punch) {
		punchRepository.delete(punch);
	}

	@Override
	public void saveOrUpdate(Punch punch) {
		punchRepository.saveOrUpdate(punch);
	}

	@Override
	public void save(Punch punch) {
		punchRepository.save(punch);
	}

	@Override
	public boolean saveIfNew(Punch punch) {
		return false;
	}

	@Override
	public List<Punch> list(Punches punches) {
		return punchRepository.findAll().merge(punches).list();
	}

	@Override
	public long count(Punches punches) {
		return punchRepository.findAll().merge(punches).count();
	}

	@Override
	public Punch uniqueObject(Punches punches) {
		return punchRepository.findAll().merge(punches).uniqueObject();
	}

	@Override
	public void update(Punch punch) {
		punchRepository.update(punch);
	}

	@Override
	public boolean exists(Punches punches) {
		Punch punch = punchRepository.findAll().merge(punches).uniqueObject();
		punchRepository.evict(punch);
		return punch != null;
	}
}
