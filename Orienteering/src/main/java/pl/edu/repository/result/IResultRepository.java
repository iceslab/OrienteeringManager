package pl.edu.repository.result;

import pl.edu.model.result.Result;
import pl.edu.repository.IRepository;
import pl.edu.repository.result.Results;

public interface IResultRepository extends IRepository<Result, Long> {

    Results findAll();
}
