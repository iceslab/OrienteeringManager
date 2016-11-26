package pl.edu.model.punch;

import lombok.Getter;
import lombok.Setter;
import pl.edu.model.BaseEntity;
import pl.edu.model.routestep.RouteStep;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Bartosz on 2016-11-26.
 */
@Entity
@Table(name = "punches")
public class Punch extends BaseEntity<Long> {
    @Id
    @Getter @Setter
    @Column
    private Long id;

    @Getter @Setter
    @Column
    private Long chip;

    @Getter @Setter
    @Column
    private Long code;

    @Getter @Setter
    @Column
    private long timestamp;

    @Getter @Setter
    @Transient
    private Correctness correctness;

    // Counts number of punches of wanted type
    public static long getNoOfCorrectnessPunches(List<Punch> punches, Correctness correctness)
    {
        List<Correctness> list = new ArrayList<>();
        list.add(correctness);
        return getNoOfCorrectnessPunches(punches, list);
    }

    // Counts number of punches of wanted types
    public static long getNoOfCorrectnessPunches(List<Punch> punches, List<Correctness> correctness)
    {
        long count = 0;
        for (Punch p : punches)
        {
            for(Correctness c : correctness)
            {
                if (p.getCorrectness().equals(c))
                {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    // Counts number of punches which should be collected according to route
    public static long getNoOfNotPresentPunches(List<Punch> punches, List<RouteStep> routeSteps)
    {
        long count = 0;
        if (punches == null)
            punches = new ArrayList<Punch>();

        Map<Long, Long> occurences = RouteStep.getCodeOccurenceCount(routeSteps);

        for(Punch punch : punches) {
            if(occurences.containsKey(punch.getCode())){
                occurences.put(punch.getCode(), occurences.get(punch.getCode()));
            }
        }

        for(Map.Entry<Long, Long> code : occurences.entrySet())
        {
            if (code.getValue() > 0)
                count++;
        }

        return count;
    }
}
