package pl.edu.model.competitor;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.model.BaseEntity;
import pl.edu.model.accommodation.availability.AccommodationAvailability;
import pl.edu.model.accommodation.reservation.AccommodationReservation;
import pl.edu.model.category.Category;
import pl.edu.model.catering.availability.CateringAvailability;
import pl.edu.model.club.Club;
import pl.edu.model.punch.Correctness;
import pl.edu.model.punch.Punch;
import pl.edu.model.result.Result;
import pl.edu.model.route.Route;
import pl.edu.model.routestep.RouteStep;
import pl.edu.repository.category.Categories;
import pl.edu.repository.route.Routes;
import pl.edu.repository.routestep.RouteSteps;
import pl.edu.service.category.ICategoryService;
import pl.edu.service.category.impl.CategoryService;
import pl.edu.service.punch.IPunchService;
import pl.edu.service.punch.impl.PunchService;
import pl.edu.service.route.IRouteService;
import pl.edu.service.route.impl.RouteService;
import pl.edu.service.routestep.IRouteStepService;
import pl.edu.service.routestep.impl.RouteStepService;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "competitors")
public class Competitor extends BaseEntity<Long> implements Comparable<Competitor>{

    private static final long serialVersionUID = -758076802868616147L;

    public Competitor(){}

    @Autowired
    @Transient
    IPunchService punchService;

    @Autowired
    @Transient
    ICategoryService categoryService;

    @Autowired
    @Transient
    IRouteService routeService;

    @Autowired
    @Transient
    IRouteStepService routeStepService;

    @Id
    @Getter @Setter
    @GeneratedValue
    @Column(columnDefinition = "INT")
    private Long id;

    @Getter @Setter
    @Column
    private String name;

    @Getter	@Setter
    @Column(name = "licence_number")
    private String licenceNumber;

    @Getter	@Setter
    @Column(name = "chip", columnDefinition = "INT")
    private Long chipNumber;

    @Getter	@Setter
    @ManyToOne
    @JoinColumn(name="relay_id",
            insertable=false, updatable=false,
            nullable=false)
    private Club club;

    @Getter	@Setter
    @Column(name = "relay_id", columnDefinition = "INT")
    private Long clubId;

    @Getter	@Setter
    @Column(name = "birth_date")
    private Date birthDate;

    @Getter	@Setter
    @Column(columnDefinition = "BIGINT")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Getter	@Setter
//    @Column
    @ManyToOne
    @JoinColumn(name="category",
            insertable=false, updatable=false,
            nullable=false)
    private Category category;

    @Getter	@Setter
    @Column(name = "category", columnDefinition = "INT")
    private Long categoryId;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "accommodation_reservations", joinColumns = {
            @JoinColumn(name = "idcompetitor", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "idaccommodation_availabilities",
                    nullable = false, updatable = false)
    })
    private Set<AccommodationAvailability> accommodationAvailabilities;

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "catering_reservations", joinColumns = {
            @JoinColumn(name = "idcompetitor", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "idcatering_availabilities",
                    nullable = false, updatable = false)
            })
    private Set<CateringAvailability> cateringAvailabilities;

    @Getter @Setter
    @Transient
    private List<Punch> punches;

    @Getter @Setter
    @Transient
    private Result result;

    @Transient
    private long goodCollections;
    public long getGoodCollections() {
        long retVal = 0;
        retVal += getCorrectPunches();
        retVal += getPresentPunches();
        return retVal;
    }

    @Transient
    private long wrongCollections;
    public long getWrongCollections() {
        long retVal = 0;
        retVal += getInvalidPunches();
        retVal += getNotPresentPunches();
        return retVal;
    }

    @Transient
    private Long notCheckedPunches;
    private Long getNotCheckedPunches() {
        if (notCheckedPunches == null && getPunches() != null)
        {
            notCheckedPunches = Punch.getNoOfCorrectnessPunches(
                    getPunches(),
                    Correctness.NOT_CHECKED);
        }
        return notCheckedPunches;
    }

    @Transient
    private Long correctPunches;
    private Long getCorrectPunches() {
        if (correctPunches == null && getPunches() != null)
        {
            correctPunches = Punch.getNoOfCorrectnessPunches(
                    getPunches(),
                    Correctness.CORRECT);
        }
        return correctPunches;
    }

    @Transient
    private Long presentPunches;
    private Long getPresentPunches() {
        if (presentPunches == null && getPunches() != null)
        {
            presentPunches = Punch.getNoOfCorrectnessPunches(
                    getPunches(),
                    Correctness.PRESENT);
        }
        return presentPunches;
    }

    @Transient
    private Long invalidPunches;
    private Long getInvalidPunches() {
        if (invalidPunches == null && getPunches() != null)
        {
            invalidPunches = Punch.getNoOfCorrectnessPunches(
                    getPunches(),
                    Correctness.INVALID);
        }
        return invalidPunches;
    }

    @Transient
    private Long notPresentPunches;
    private Long getNotPresentPunches() {
        if (notPresentPunches == null) {
            Category category = categoryService.uniqueObject(Categories.findAll().withId(getCategoryId()));
            Route route = routeService.uniqueObject(Routes.findAll().withCategory(category.getId()));
            List<RouteStep> routeSteps = routeStepService.list(RouteSteps.findAll().withRouteId(route.getId()));

            notPresentPunches = Punch.getNoOfNotPresentPunches(
                    getPunches(),
                    routeSteps);
        }
        return notPresentPunches;
    }

    @Override
    public int compareTo(Competitor other) {
        int retVal = 0;
        Competitor left = this;
        Competitor right = other;

        Long leftPunches = left.getWrongCollections();
        Long rightPunches = right.getWrongCollections();

        // When right competitor made more mistakes
        if (leftPunches < rightPunches)
        {
            retVal = -1;
        }
        // When left competitor made more mistakes
        else if (leftPunches > rightPunches)
        {
            retVal = 1;
        }
        // When competitors made the same amount of mistakes (this means none too)
        else
        {
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
