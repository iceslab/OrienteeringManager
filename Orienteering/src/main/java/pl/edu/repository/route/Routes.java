package pl.edu.repository.route;

import org.apache.commons.lang3.StringUtils;
import pl.edu.model.route.Route;
import pl.edu.repository.OrderType;
import pl.edu.repository.Queryable;

import java.util.List;

public abstract class Routes extends Queryable<Route, Long> {

	private static final long serialVersionUID = -5848249886489304600L;

	protected String name;
    protected Long category;

	protected Routes() {
	}

	public Routes withName(String name) {
		this.name = name;
		return this;
	}

    public Routes withCategory(Long category) {
        this.category = category;
        return this;
    }

	public Routes withId(Long id) {
		return (Routes) super.withId(id);
	}

	public Routes addOrder(OrderType orderType, String sortProperty) {
		return (Routes) super.addOrder(orderType, sortProperty);
	}

	public Routes loadWith(String... propertyNames) {
		return (Routes) super.loadWith(propertyNames);
	}

	public Routes paginate(int startingAt, int maxResults) {
		return (Routes) super.paginate(startingAt, maxResults);
	}

	public Routes randomOrder() {
		return (Routes) super.randomOrder();
	}

	public Routes merge(Routes other) {
		super.merge(other);
		if (StringUtils.isNotBlank(other.name)) {
			this.name = other.name;
		}
        if (other.category != null) {
            this.category = other.category;
        }
		return this;
	}

	public static Routes findAll() {
		return new Routes() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public Route uniqueObject() {
				return null;
			}

			@Override
			public List<Route> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}
}
