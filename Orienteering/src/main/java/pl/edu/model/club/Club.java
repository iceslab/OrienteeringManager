package pl.edu.model.club;

import lombok.Getter;
import lombok.Setter;
import pl.edu.model.BaseEntity;
import pl.edu.model.accommodation.availability.AccommodationAvailability;
import pl.edu.model.competitor.Competitor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clubs")
public class Club extends BaseEntity<Long> implements Comparable<Club>{

    private static final long serialVersionUID = -758076802868616147L;

    public Club(){}

    @Id
    @Getter @Setter
    @GeneratedValue
    @Column(name = "id", columnDefinition = "INT")
    private Long id;

    @Getter @Setter
    @Column
    private String name;

    @Getter	@Setter
    @Column
    private String address;

    @Getter	@Setter
    @Column(name = "agent_name")
    private String agentName;

    @Getter	@Setter
    @Column(name = "agent_surname")
    private String agentSurname;

    @Getter	@Setter
    @Column(name = "club_number")
    private String clubNumber;

    @Getter @Setter
//    @Transient
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "club")
    private List<Competitor> competitors;

    @Getter	@Setter
    @Column(name = "phone_number")
    private String phoneNumber;

    @Transient
    private Long overallRunningTime;
    public Long getOverallRunningTime()
    {
        if (overallRunningTime == null && competitors != null)
        {
            overallRunningTime = new Long(0);
            for(Competitor c : competitors)
            {
                overallRunningTime += c.getResult().getRunningTime();
            }
        }
        return overallRunningTime;
    }

    @Override
    public int compareTo(Club other) {
        // TODO: Consider total errors made by competitors
        int retVal = 0;
        Club left = this;
        Club right = other;

        if (left.getOverallRunningTime() < right.getOverallRunningTime())
            retVal = -1;
        else if (left.getOverallRunningTime() > right.getOverallRunningTime())
            retVal = 1;
        else
            retVal = 0;

        return retVal;
    }
}
