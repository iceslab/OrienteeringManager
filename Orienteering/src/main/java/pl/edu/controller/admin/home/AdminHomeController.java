package pl.edu.controller.admin.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import pl.edu.model.competitor.Competitor;
import pl.edu.repository.competitor.Competitors;
import pl.edu.service.competitor.ICompetitorService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bartosz on 23.04.16.
 */
@Controller("adminHomeController")
public class AdminHomeController {

    @Autowired
    private ICompetitorService competitorService;

    @RequestMapping(value = {"/admin", "/admin/"})
    public String home(ModelMap model){
        List<Competitor> competitors = competitorService.list(Competitors.findAll());
        model.addAttribute("competitors", competitors);
        return "index";
    }
}