package pl.edu.repository.result;

import org.apache.commons.lang3.StringUtils;
import pl.edu.model.category.Category;
import pl.edu.model.club.Club;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.result.Result;
import pl.edu.repository.OrderType;
import pl.edu.repository.Queryable;

import java.util.List;

public abstract class Results extends Queryable<Result, Long> {

	private static final long serialVersionUID = -5848249886489304600L;

    protected Long chip;
    protected Long startTime;
    protected Long checkTime;
    protected Long finishTime;

	protected Results() {
	}

	public Results withChip(Long chip) {
		this.chip = chip;
		return this;
	}

    public Results withStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Results withCheckTime(Long checkTime) {
        this.checkTime = checkTime;
        return this;
    }

    public Results withFinishTime(Long finishTime) {
        this.finishTime = finishTime;
        return this;
    }

	public Results withId(Long id) {
		return (Results) super.withId(id);
	}

	public Results addOrder(OrderType orderType, String sortProperty) {
		return (Results) super.addOrder(orderType, sortProperty);
	}

	public Results loadWith(String... propertyNames) {
		return (Results) super.loadWith(propertyNames);
	}

	public Results paginate(int startingAt, int maxResults) {
		return (Results) super.paginate(startingAt, maxResults);
	}

	public Results randomOrder() {
		return (Results) super.randomOrder();
	}

	public Results merge(Results other) {
		super.merge(other);

        if (other.chip != null) {
            this.chip = other.chip;
        }
        if (other.startTime != null) {
            this.startTime = other.startTime;
        }
        if (other.checkTime != null) {
            this.checkTime = other.checkTime;
        }
        if (other.finishTime != null) {
            this.finishTime = other.finishTime;
        }

		return this;
	}

	public static Results findAll() {
		return new Results() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public Result uniqueObject() {
				return null;
			}

			@Override
			public List<Result> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}
}
