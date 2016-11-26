package pl.edu.repository.punch;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.punch.Punch;
import pl.edu.repository.CommonCriteriaQueryable;
import pl.edu.utils.ClassUtils;

import java.util.List;

public class CriteriaPunches extends Punches {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3544776035574497650L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaPunches(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria) {

        if (chip != null) {
            criteria.add(Restrictions.eq("chip", chip));
        }
        if (code != null) {
            criteria.add(Restrictions.eq("code", code));
        }
        if (timestamp != null) {
            criteria.add(Restrictions.eq("timestamp", timestamp));
        }
        if (correctness != null) {
            criteria.add(Restrictions.eq("correctness", correctness));
        }

		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<Punch> list() {
		return CommonCriteriaQueryable.list(this,
                modifyCriteria(criteria),
                criteria2,
                ClassUtils.getMapAndCollectionsFrom(Punch.class));
	}

	@Override
	public Punch uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}
}
