package pl.edu.model.routestep;

import lombok.Getter;
import lombok.Setter;
import pl.edu.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bartosz on 2016-11-26.
 */
@Entity
@Table(name = "route_steps")
public class RouteStep extends BaseEntity<Long> implements Comparable<RouteStep>{
    @Id
    @Getter @Setter
    @Column
    private Long id;

    @Getter @Setter
    @Column
    private Long order;

    @Getter @Setter
    @Column
    private Long code;

    @Getter @Setter
    @Column(name = "route_id")
    private long routeId;

    public static Map<Long, Long> getCodeOccurenceCount(List<RouteStep> rs)
    {
        Map<Long, Long> dict = new HashMap<>();

        for(RouteStep e : rs)
        {
            if (dict.containsKey(e.getCode()))
                dict.put(e.getCode(), dict.get(e.getCode()) + 1);
            else
                dict.put(e.getCode(), 1L);
        }

        return dict;
    }

    @Override
    public int compareTo(RouteStep other) {
        return order.compareTo(other.order);
    }
}
