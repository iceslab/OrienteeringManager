package pl.edu.model.competitor;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.edu.model.BaseEntity;
import pl.edu.model.accommodation.availability.AccommodationAvailability;
import pl.edu.model.category.Category;
import pl.edu.model.catering.availability.CateringAvailability;
import pl.edu.model.club.Club;
import pl.edu.model.punch.Correctness;
import pl.edu.model.punch.Punch;
import pl.edu.model.result.Result;
import pl.edu.model.route.Route;
import pl.edu.model.routestep.RouteStep;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "competitors")
public class Competitor extends BaseEntity<Long> implements Comparable<Competitor> {

    private static final long serialVersionUID = -758076802868616147L;
    @Id
    @Getter
    @Setter
    @GeneratedValue
    @Column(columnDefinition = "INT")
    private Long id;
    @Getter
    @Setter
    @Column
    private String name;
    @Getter
    @Setter
    @Column(name = "licence_number")
    private String licenceNumber;
    @Getter
    @Setter
    @Column(name = "chip", columnDefinition = "INT")
    private Long chipNumber;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "relay_id",
            insertable = false, updatable = false,
            nullable = false)
    private Club club;
    @Getter
    @Setter
    @Column(name = "relay_id", columnDefinition = "INT")
    private Long clubId;
    @Getter
    @Setter
    @Column(name = "birth_date")
    private Date birthDate;
    @Getter
    @Setter
    @Column(columnDefinition = "BIGINT")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @Getter
    @Setter
//    @Column
    @ManyToOne
    @JoinColumn(name = "category",
            insertable = false, updatable = false,
            nullable = false)
    private Category category;
    @Getter
    @Setter
    @Column(name = "category", columnDefinition = "INT")
    private Long categoryId;
    @Getter
    @Setter
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "accommodation_reservations", joinColumns = {
            @JoinColumn(name = "idcompetitor", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "idaccommodation_availabilities",
                    nullable = false, updatable = false)
            })
    private Set<AccommodationAvailability> accommodationAvailabilities;
    @Getter
    @Setter
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "catering_reservations", joinColumns = {
            @JoinColumn(name = "idcompetitor", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "idcatering_availabilities",
                    nullable = false, updatable = false)
            })
    private Set<CateringAvailability> cateringAvailabilities;
    @Getter
    @Setter
//    @Transient
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chip")
//    @JoinTable(name = "punches",
//            joinColumns = {@JoinColumn(name = "chip")},
//            inverseJoinColumns = {@JoinColumn(name = "chip")})
//    @JoinColumn(name = "chip")
    private List<Punch> punches;
    @Getter
    @Setter
//    @Transient
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToOne
    @JoinColumn(name = "chip", insertable = false, updatable = false)
    private Result result;

    public Competitor() {
    }

    public List<RouteStep> getRouteSteps() {
        return getCategory().getRoute().getRouteSteps();
    }

    public void setRouteSteps(List<RouteStep> routeSteps) {
        Route route = category.getRoute();
        route.setRouteSteps(routeSteps);
        category.setRoute(route);
    }

    public long getGoodCollections() {
        long retVal = 0;
        retVal += getCorrectPunches();
        retVal += getPresentPunches();
        return retVal;
    }

    public long getWrongCollections() {
        long retVal = 0;
        retVal += getInvalidPunches();
        retVal += getNotPresentPunches();
        return retVal;
    }

    private Long getNotCheckedPunches() {
        return Punch.getNoOfCorrectnessPunches(
                getPunches(),
                Correctness.NOT_CHECKED);
    }

    private Long getCorrectPunches() {
        return Punch.getNoOfCorrectnessPunches(
                getPunches(),
                Correctness.CORRECT);
    }

    private Long getPresentPunches() {
        return Punch.getNoOfCorrectnessPunches(
                getPunches(),
                Correctness.PRESENT);
    }

    private Long getInvalidPunches() {
        return Punch.getNoOfCorrectnessPunches(
                getPunches(),
                Correctness.INVALID);
    }

    private Long getNotPresentPunches() {
        return Punch.getNoOfNotPresentPunches(
                getPunches(),
                getRouteSteps());
    }

    @Override
    public int compareTo(Competitor other) {
        int retVal = 0;
        Competitor left = this;
        Competitor right = other;

        Long leftPunches = left.getWrongCollections();
        Long rightPunches = right.getWrongCollections();

        // When right competitor made more mistakes
        if (leftPunches < rightPunches) {
            retVal = -1;
        }
        // When left competitor made more mistakes
        else if (leftPunches > rightPunches) {
            retVal = 1;
        }
        // When competitors made the same amount of mistakes (this means none too)
        else {
            long leftTime = left.getResult().getRunningTime();
            long rightTime = right.getResult().getRunningTime();
            // When left one ran longer
            if (leftTime > rightTime)
                retVal = 1;
                // When right one ran longer
            else if (leftTime < rightTime)
                retVal = -1;
                // When both ran the same time
            else
                retVal = 0;
        }

        return retVal;
    }
}
