package pl.edu.repository.routestep;

import org.apache.commons.lang3.StringUtils;
import pl.edu.model.category.Category;
import pl.edu.model.club.Club;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.routestep.RouteStep;
import pl.edu.repository.OrderType;
import pl.edu.repository.Queryable;

import java.util.List;

public abstract class RouteSteps extends Queryable<RouteStep, Long> {

	private static final long serialVersionUID = -5848249886489304600L;
    protected Long order;
    protected Long code;
    protected Long routeId;

	protected RouteSteps() {
	}

	public RouteSteps withOrder(Long order) {
		this.order = order;
		return this;
	}

    public RouteSteps withCode(Long code) {
        this.code = code;
        return this;
    }

    public RouteSteps withRouteId(Long routeId) {
        this.routeId = routeId;
        return this;
    }

	public RouteSteps withId(Long id) {
		return (RouteSteps) super.withId(id);
	}

	public RouteSteps addOrder(OrderType orderType, String sortProperty) {
		return (RouteSteps) super.addOrder(orderType, sortProperty);
	}

	public RouteSteps loadWith(String... propertyNames) {
		return (RouteSteps) super.loadWith(propertyNames);
	}

	public RouteSteps paginate(int startingAt, int maxResults) {
		return (RouteSteps) super.paginate(startingAt, maxResults);
	}

	public RouteSteps randomOrder() {
		return (RouteSteps) super.randomOrder();
	}

	public RouteSteps merge(RouteSteps other) {
		super.merge(other);

        if (other.order != null) {
            this.order = other.order;
        }
        if (other.code != null) {
            this.code = other.code;
        }
        if (other.routeId != null) {
            this.routeId = other.routeId;
        }
		return this;
	}

	public static RouteSteps findAll() {
		return new RouteSteps() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public RouteStep uniqueObject() {
				return null;
			}

			@Override
			public List<RouteStep> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}
}
