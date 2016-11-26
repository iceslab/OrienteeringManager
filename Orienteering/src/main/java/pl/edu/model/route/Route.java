package pl.edu.model.route;

import lombok.Getter;
import lombok.Setter;
import pl.edu.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
