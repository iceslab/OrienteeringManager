package pl.edu.service.result;

import pl.edu.model.competitor.Competitor;
import pl.edu.model.result.Result;
import pl.edu.repository.competitor.Competitors;
import pl.edu.repository.result.Results;
import pl.edu.service.IService;

import java.util.List;

public interface IResultService extends IService {

	void delete(Result result);

	void update(Result result);
	
	boolean exists(Results results);
	
	void saveOrUpdate(Result result);
	
	void save(Result result);
	
	boolean saveIfNew(Result result);

	List<Result> list(Results results);

	long count(Results results);

	Result uniqueObject(Results results);
}
