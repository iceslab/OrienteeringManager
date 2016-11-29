package pl.edu.model.route;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.edu.model.BaseEntity;
import pl.edu.model.routestep.RouteStep;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Bartosz on 2016-11-26.
 */
@Entity
@Table(name = "routes")
public class Route extends BaseEntity<Long> {
    @Id
    @Getter @Setter
    @Column
    private Long id;

    @Getter @Setter
    @Column
    private String name;

    @Getter @Setter
    @Column
    private Long category;

    @Getter @Setter
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "routeId")
    private List<RouteStep> routeSteps;
}
