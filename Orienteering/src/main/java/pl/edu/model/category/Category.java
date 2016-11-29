package pl.edu.model.category;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.edu.model.BaseEntity;
import pl.edu.model.route.Route;
import pl.edu.model.routestep.RouteStep;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity<Long> {

    private static final long serialVersionUID = -758076802868616147L;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    @Id
    @Getter @Setter
    @GeneratedValue
    @Column(columnDefinition = "INT")
    private Long id;

    @Getter @Setter
    @Column
    private String name;

    @Getter @Setter
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "category")
    private List<Route> routes;

    public Route getRoute(){
        return routes.get(0);
    }

    public void setRoute(Route route){
        routes.set(0, route);
    }

}
