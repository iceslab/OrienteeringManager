package pl.edu.repository.routestep;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.routestep.RouteStep;
import pl.edu.repository.CommonCriteriaQueryable;
import pl.edu.utils.ClassUtils;

import java.util.List;

public class CriteriaRouteSteps extends RouteSteps {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3544776035574497650L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaRouteSteps(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria) {

        if (order != null) {
            criteria.add(Restrictions.eq("order", order));
        }
        if (code != null) {
            criteria.add(Restrictions.eq("code", code));
        }
        if (routeId != null) {
            criteria.add(Restrictions.eq("routeId", routeId));
        }

		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<RouteStep> list() {
		return CommonCriteriaQueryable.list(this,
                modifyCriteria(criteria),
                criteria2,
                ClassUtils.getMapAndCollectionsFrom(Competitor.class));
	}

	@Override
	public RouteStep uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}
}
