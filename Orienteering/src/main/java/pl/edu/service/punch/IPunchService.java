package pl.edu.service.punch;

import pl.edu.model.competitor.Competitor;
import pl.edu.model.punch.Punch;
import pl.edu.repository.competitor.Competitors;
import pl.edu.repository.punch.Punches;
import pl.edu.service.IService;

import java.util.List;

public interface IPunchService extends IService {

	void delete(Punch punch);

	void update(Punch punch);
	
	boolean exists(Punches punches);
	
	void saveOrUpdate(Punch punch);
	
	void save(Punch punch);
	
	boolean saveIfNew(Punch punch);

	List<Punch> list(Punches punches);

	long count(Punches punches);

	Punch uniqueObject(Punches punches);
}
