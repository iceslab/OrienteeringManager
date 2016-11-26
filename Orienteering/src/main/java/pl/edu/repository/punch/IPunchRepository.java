package pl.edu.repository.punch;

import pl.edu.model.competitor.Competitor;
import pl.edu.model.punch.Punch;
import pl.edu.repository.IRepository;

public interface IPunchRepository extends IRepository<Punch, Long> {

    Punches findAll();
}
