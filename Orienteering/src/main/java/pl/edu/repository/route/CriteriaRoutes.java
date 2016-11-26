package pl.edu.repository.route;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.route.Route;
import pl.edu.repository.CommonCriteriaQueryable;
import pl.edu.utils.ClassUtils;

import java.util.List;

public class CriteriaRoutes extends Routes {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3544776035574497650L;
	protected Criteria criteria;
	protected Criteria criteria2;

	public CriteriaRoutes(Criteria criteria, Criteria criteria2) {
		this.criteria = criteria;
		this.criteria2 = criteria2;
	}

	public Criteria modifyCriteria(Criteria criteria) {
        if (StringUtils.isNotBlank(name)) {
            criteria.add(Restrictions.eq("name", name));
        }
        if (category != null) {
            criteria.add(Restrictions.eq("category", category));
        }

		return criteria;
	}

	@Override
	public long count() {
		return CommonCriteriaQueryable.count(this, modifyCriteria(criteria));
	}

	@Override
	public List<Route> list() {
		return CommonCriteriaQueryable.list(this,
                modifyCriteria(criteria),
                criteria2,
                ClassUtils.getMapAndCollectionsFrom(Competitor.class));
	}

	@Override
	public Route uniqueObject() {
		return CommonCriteriaQueryable.uniqueObject(this, modifyCriteria(criteria));
	}
}
