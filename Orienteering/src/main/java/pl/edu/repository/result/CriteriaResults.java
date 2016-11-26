package pl.edu.repository.result;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.result.Result;
import pl.edu.repository.CommonCriteriaQueryable;
import pl.edu.utils.ClassUtils;

import java.util.List;

public class CriteriaResults extends Results {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3544776035574497650L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaResults(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria) {
        if (chip != null) {
            criteria.add(Restrictions.eq("chip", chip));
        }
        if (startTime != null) {
            criteria.add(Restrictions.eq("startTime", startTime));
        }
        if (checkTime != null) {
            criteria.add(Restrictions.eq("checkTime", checkTime));
        }
        if (finishTime != null) {
            criteria.add(Restrictions.eq("finishTime", finishTime));
        }

		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<Result> list() {
		return CommonCriteriaQueryable.list(this,
                modifyCriteria(criteria),
                criteria2,
                ClassUtils.getMapAndCollectionsFrom(Result.class));
	}

	@Override
	public Result uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}
}
