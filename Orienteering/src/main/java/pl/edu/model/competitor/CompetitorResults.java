package pl.edu.model.competitor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.model.category.Category;
import pl.edu.model.punch.Correctness;
import pl.edu.model.punch.Punch;
import pl.edu.model.route.Route;
import pl.edu.model.routestep.RouteStep;
import pl.edu.repository.category.Categories;
import pl.edu.repository.punch.Punches;
import pl.edu.repository.route.Routes;
import pl.edu.repository.routestep.RouteSteps;
import pl.edu.service.category.impl.CategoryService;
import pl.edu.service.punch.impl.PunchService;
import pl.edu.service.route.impl.RouteService;
import pl.edu.service.routestep.impl.RouteStepService;

import java.util.List;

/**
 * Created by Bartosz on 2016-11-26.
 */
public class CompetitorResults {
    @Autowired
    PunchService punchService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    RouteService routeService;

    @Autowired
    RouteStepService routeStepService;

    public CompetitorResults(Competitor competitor){
        this.competitor = competitor;
        this.competitor.setPunches(punchService.list(Punches.findAll().withChip(this.competitor.getChipNumber())));
    }

    @Getter @Setter
    private Competitor competitor;

    private long goodCollections;
    public long getGoodCollections() {
        long retVal = 0;
        retVal += getCorrectPunches();
        retVal += getPresentPunches();
        return retVal;
    }

    private long wrongCollections;
    public long getWrongCollections() {
        long retVal = 0;
        retVal += getInvalidPunches();
        retVal += getNotPresentPunches();
        return retVal;
    }

    private Long notCheckedPunches;
    private Long getNotCheckedPunches() {
        if (notCheckedPunches == null && competitor.getPunches() != null)
        {
            notCheckedPunches = Punch.getNoOfCorrectnessPunches(
                    competitor.getPunches(),
                    Correctness.NOT_CHECKED);
        }
        return notCheckedPunches;
    }

    private Long correctPunches;
    private Long getCorrectPunches() {
        if (correctPunches == null && competitor.getPunches() != null)
        {
            correctPunches = Punch.getNoOfCorrectnessPunches(
                    competitor.getPunches(),
                    Correctness.CORRECT);
        }
        return correctPunches;
    }

    private Long presentPunches;
    private Long getPresentPunches() {
        if (presentPunches == null && competitor.getPunches() != null)
        {
            presentPunches = Punch.getNoOfCorrectnessPunches(
                    competitor.getPunches(),
                    Correctness.PRESENT);
        }
        return presentPunches;
    }

    private Long invalidPunches;
    private Long getInvalidPunches() {
        if (invalidPunches == null && competitor.getPunches() != null)
        {
            invalidPunches = Punch.getNoOfCorrectnessPunches(
                    competitor.getPunches(),
                    Correctness.INVALID);
        }
        return invalidPunches;
    }

    private Long notPresentPunches;
    private Long getNotPresentPunches() {
//        if (notPresentPunches == null) {
//            Category category = categoryService.uniqueObject(Categories.findAll().withId(competitor.getCategoryId()));
//            Route route = routeService.uniqueObject(Routes.findAll().withCategory(category.getId()));
//            List<RouteStep> routeSteps = routeStepService.list(RouteSteps.findAll().withRouteId(route.getId()));
//
//            notPresentPunches = Punch.getNoOfNotPresentPunches(
//                    competitor.getPunches(),
//                    routeSteps);
//        }
        return notPresentPunches;
    }
}
