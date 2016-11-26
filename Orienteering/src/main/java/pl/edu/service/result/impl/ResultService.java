package pl.edu.service.result.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.model.result.Result;
import pl.edu.repository.result.IResultRepository;
import pl.edu.repository.result.Results;
import pl.edu.service.result.IResultService;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ResultService implements IResultService {

	private static final long serialVersionUID = -7007444203780713873L;

	@Autowired
	private IResultRepository resultRepository;

	@Override
	public void delete(Result result) {
		resultRepository.delete(result);
	}

	@Override
	public void saveOrUpdate(Result result) {
		resultRepository.saveOrUpdate(result);
	}

	@Override
	public void save(Result result) {
		resultRepository.save(result);
	}

	@Override
	public boolean saveIfNew(Result result) {
		return false;
	}

	@Override
	public List<Result> list(Results results) {
		return resultRepository.findAll().merge(results).list();
	}

	@Override
	public long count(Results results) {
		return resultRepository.findAll().merge(results).count();
	}

	@Override
	public Result uniqueObject(Results results) {
		return resultRepository.findAll().merge(results).uniqueObject();
	}

	@Override
	public void update(Result result) {
		resultRepository.update(result);
	}

	@Override
	public boolean exists(Results results) {
		Result result = resultRepository.findAll().merge(results).uniqueObject();
		resultRepository.evict(result);
		return result != null;
	}
}
