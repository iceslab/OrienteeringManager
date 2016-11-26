package pl.edu.model.result;

import lombok.Getter;
import lombok.Setter;
import pl.edu.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by Bartosz on 2016-11-26.
 */
@Entity
@Table(name = "results")
public class Result extends BaseEntity<Long> {
    @Id
    @Getter @Setter
    @Column
    private Long chip;
    public Long getId() {
        return getChip();
    }

    public void setId(Long id) {
        setChip(id);
    }

    @Getter @Setter
    @Column(name = "start_time")
    private Long startTime;

    @Getter @Setter
    @Column(name = "check_time")
    private Long checkTime;

    @Getter @Setter
    @Column(name = "finish_time")
    private Long finishTime;

    public long getRunningTime()
    {
        return finishTime - startTime;
    }
}
