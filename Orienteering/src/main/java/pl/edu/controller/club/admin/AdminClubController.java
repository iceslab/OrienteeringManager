package pl.edu.controller.club.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.controller.BaseController;
import pl.edu.controller.club.form.ClubForm;
import pl.edu.controller.club.form.ClubValidator;
import pl.edu.model.club.Club;
import pl.edu.repository.club.Clubs;
import pl.edu.service.club.IClubService;

import java.util.Iterator;
import java.util.List;

/**
 * Created by bartosz on 23.04.16.
 */
@Controller("adminClubController")
public class AdminClubController extends BaseController {

    @Autowired
    IClubService clubService;

    @ModelAttribute("clubForm")
    ClubForm clubForm(){
        return new ClubForm();
    }

    @RequestMapping(value = {"/admin/edit/club", "/admin/edit/club/"})
    public String clubRegister(@ModelAttribute("clubForm") ClubForm form,
                                        BindingResult bindingResult){
        return "admin/edit/club_form";
    }

    @RequestMapping(value = {"/admin/edit/club", "/admin/edit/club/"},
            method= RequestMethod.POST, params="action=save")
    public String saveClub(@ModelAttribute("clubForm") ClubForm form,
                                    BindingResult bindingResult) {
        String resultView = "/admin/edit/club_form";
        ClubValidator validator = new ClubValidator();
        validator.validate(form, bindingResult);
        if(!validator.hasErrors())
        {
            try {
                clubService.saveOrUpdate(form.getClub());
                resultView = "redirect:/admin/club";
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return resultView;
    }

    @RequestMapping(value = {"/admin/edit/club", "/admin/edit/club/"},
            method=RequestMethod.POST, params="action=cancel")
    public String cancelClub() {
        return "redirect:/admin/club";
    }
}
