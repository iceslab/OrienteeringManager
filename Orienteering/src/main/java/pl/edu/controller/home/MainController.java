package pl.edu.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.controller.BaseController;
import pl.edu.model.club.Club;
import pl.edu.model.competitor.Competitor;
import pl.edu.model.punch.Punch;
import pl.edu.model.result.Result;
import pl.edu.model.user.Role;
import pl.edu.repository.club.Clubs;
import pl.edu.repository.competitor.Competitors;
import pl.edu.repository.punch.Punches;
import pl.edu.repository.result.Results;
import pl.edu.service.club.IClubService;
import pl.edu.service.competitor.ICompetitorService;
import pl.edu.service.punch.IPunchService;
import pl.edu.service.result.IResultService;
import pl.edu.utils.RoleUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 * Created by bartosz on 09.04.16.
 */

@Controller("mainController")
public class MainController extends BaseController {
    @Autowired
    private IPunchService punchService;

    @Autowired
    private IResultService resultService;

    @Autowired
    private ICompetitorService competitorService;

    @Autowired
    private IClubService clubService;

    @RequestMapping("/")
    public String homepage(ModelMap model) {
        Role effectiveRole = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // When there is authenticated user
        if(authentication.isAuthenticated()){
            Collection authorities = authentication.getAuthorities();

            // If there is only one authority (more means there is error)
            if(authorities.size() == 1) {
                // Iterate over enum values and check if there is granted authority
                for (Role role : Role.values()) {
                    SimpleGrantedAuthority simpleGrantedAuthority =
                            new SimpleGrantedAuthority(role.getCodeWithPrefix());

                    // If there is granted authority
                    if(authorities.contains(simpleGrantedAuthority)){
                        effectiveRole = role;
                        break;
                    }
                }
            }
        }

        // When user is guest
        if(effectiveRole == null){
            List<Competitor> competitors = competitorService.list(Competitors.findAll());
            model.addAttribute("competitors", competitors);
        }

        //model.addAttribute("attribute", "Zmienna z modelu");

        // Redirect to a proper page or show default homepage
        return RoleUtils.getHomeFromRole(effectiveRole);
    }

    @RequestMapping(value = {"/results", "/results/"})
    public String results(){
        String resultPage = "";

        List<Club> relays = clubService.list(Clubs.findAll());
        for(Club relay : relays){
            List<Competitor> competitors = competitorService.list(Competitors.findAll().withClub(relay));
            for(Competitor competitor : competitors){
                Result result = resultService.uniqueObject(Results.findAll().withChip(competitor.getChipNumber()));
                List<Punch> punches = punchService.list(Punches.findAll().withChip(competitor.getChipNumber()));

                competitor.setResult(result);
                competitor.setPunches(punches);
            }
            relay.setCompetitors(competitors);
        }

        ClassifyAll(relays);
        return resultPage;
    }

    private void ClassifyAll(List<Club> relays){
        ClassifyCompetitors(relays);
        ClassifyRelays(relays);
    }

    private void ClassifyCompetitors(List<Club> relays){
        for(Club relay : relays){
            List<Competitor> competitors = relay.getCompetitors();
            competitors.sort(Competitor::compareTo);
            relay.setCompetitors(competitors);
        }
    }

    private void ClassifyRelays(List<Club> relays){
        relays.sort(Club::compareTo);
    }
}