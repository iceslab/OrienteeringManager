package pl.edu.model.punch;

import lombok.Getter;
import lombok.Setter;
import pl.edu.model.BaseEntity;
import pl.edu.model.competitor.Competitor;
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
public class Punch extends BaseEntity<Long> implements Comparable<Punch> {
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
    private Long timestamp;

    @Getter @Setter
    @Transient
    private Correctness correctness;

//    @Getter @Setter
//    @ManyToOne(cascade = CascadeType.ALL, optional = false)
//    @JoinColumn(name = "chip", insertable = false, updatable = false)
//    private Competitor competitor;

    public Punch(){
        id = null;
        chip = null;
        code = null;
        timestamp = null;
        correctness = Correctness.NOT_CHECKED;
    }

    // Checks correctness of Punch list according to RouteStep list
    // Method assumes that Punches and RouteSteps are not sorted
    public static void checkCorrectnessUnsorted(List<Punch> punches, List<RouteStep> routeSteps, boolean allowRepeats) {
        if (punches == null)
            throw new IllegalArgumentException("Punches list refers to null");
        if (routeSteps == null)
            throw new IllegalArgumentException("RouteSteps list refers to null");

        punches.sort(Punch::compareTo);
        routeSteps.sort(RouteStep::compareTo);

        // Associative array of RouteStep code occurences
        Map<Long, Long> routeStepsAssociative = RouteStep.getCodeOccurenceCount(routeSteps);

        // Normal "for loop" only because of using index comparision
        for (int i = 0; i < punches.size(); i++)
        {
            // If Code matches in both lists set to CORRECT
            if (i < routeSteps.size() && punches.get(i).getCode() == routeSteps.get(i).getCode())
            {
                Punch p = punches.get(i);
                p.setCorrectness(Correctness.CORRECT);
                punches.set(i, p);
                if(!allowRepeats)
                    routeStepsAssociative.put(punches.get(i).getCode(),
                            routeStepsAssociative.get(punches.get(i).getCode()) - 1);
            }
            else
            {
                // On other cases than mentioned below Punch is invalid
                Punch p = punches.get(i);
                p.setCorrectness(Correctness.INVALID);
                punches.set(i, p);
            }
        }

        // Check invalid punches if they're misplaced
        for (Punch punch : punches)
        {
            if (punch.getCorrectness().equals(Correctness.INVALID))
            {
                // If element exists on route
                if (routeStepsAssociative.containsKey(punch.getCode()) == true)
                {
                    Long value = routeStepsAssociative.get(punch.getCode());
                    // If element exists and was collected proper number of times
                    if (value > 0)
                    {
                        if(!allowRepeats)
                            routeStepsAssociative.put(punch.getCode(),
                                    routeStepsAssociative.get(punch.getCode()) - 1);

                        punch.setCorrectness(Correctness.PRESENT);
                    }
                }
            }
        }
    }

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
        if(punches != null && correctness != null){
            for (Punch p : punches) {
                for(Correctness c : correctness) {
                    if (p.getCorrectness().equals(c)) {
                        count++;
                        break;
                    }
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
                occurences.put(punch.getCode(), occurences.get(punch.getCode()) - 1);
            }
        }

        for(Map.Entry<Long, Long> code : occurences.entrySet())
        {
            if (code.getValue() > 0)
                count++;
        }

        return count;
    }

    @Override
    public int compareTo(Punch other) {
        return timestamp.compareTo(other.timestamp);
    }
}
