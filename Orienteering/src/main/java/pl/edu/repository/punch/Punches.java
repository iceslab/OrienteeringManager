package pl.edu.repository.punch;

import pl.edu.model.punch.Correctness;
import pl.edu.model.punch.Punch;
import pl.edu.repository.OrderType;
import pl.edu.repository.Queryable;

import java.util.List;

public abstract class Punches extends Queryable<Punch, Long> {

	private static final long serialVersionUID = -5848249886489304600L;

    protected Long chip;
    protected Long code;
    protected Long timestamp;
    protected Correctness correctness;

	protected Punches() {
	}

	public Punches withChip(Long chip) {
		this.chip = chip;
		return this;
	}

    public Punches withCode(Long code) {
        this.code = code;
        return this;
    }

    public Punches withTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Punches withCorrectness(Correctness correctness) {
        this.correctness = correctness;
        return this;
    }

	public Punches withId(Long id) {
		return (Punches) super.withId(id);
	}

	public Punches addOrder(OrderType orderType, String sortProperty) {
		return (Punches) super.addOrder(orderType, sortProperty);
	}

	public Punches loadWith(String... propertyNames) {
		return (Punches) super.loadWith(propertyNames);
	}

	public Punches paginate(int startingAt, int maxResults) {
		return (Punches) super.paginate(startingAt, maxResults);
	}

	public Punches randomOrder() {
		return (Punches) super.randomOrder();
	}

	public Punches merge(Punches other) {
		super.merge(other);

        if (other.chip != null) {
            this.chip = other.chip;
        }
        if (other.code != null) {
            this.code = other.code;
        }
        if (other.timestamp != null) {
            this.timestamp = other.timestamp;
        }
        if (other.correctness != null) {
            this.correctness = other.correctness;
        }

		return this;
	}

	public static Punches findAll() {
		return new Punches() {

			private static final long serialVersionUID = -6804576481886404146L;

			@Override
			public Punch uniqueObject() {
				return null;
			}

			@Override
			public List<Punch> list() {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}
		};
	}
}
