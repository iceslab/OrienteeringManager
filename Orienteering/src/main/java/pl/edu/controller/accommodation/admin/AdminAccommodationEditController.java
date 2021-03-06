package pl.edu.controller.accommodation.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.controller.BaseController;
import pl.edu.controller.accommodation.form.AccommodationForm;
import pl.edu.controller.competitor.form.CompetitorForm;
import pl.edu.model.accommodation.Accommodation;
import pl.edu.repository.accommodation.Accommodations;
import pl.edu.service.accommodation.IAccommodationService;

import java.util.List;

/**
 * Created by bartosz on 23.04.16.
 */
@Controller("adminAccommodationEditController")
public class AdminAccommodationEditController extends BaseController{

    @Autowired
    IAccommodationService accommodationService;

    @ModelAttribute("accommodationForm")
    AccommodationForm accommodationForm(){
        return new AccommodationForm();
    }

    @RequestMapping(value = {"/admin/edit/accommodation", "/admin/edit/accommodation/"})
    public String accommodationRegister(@ModelAttribute("accommodationForm") AccommodationForm form,
                       BindingResult bindingResult){
        return "admin/edit/accommodation_form";
    }

    @RequestMapping(value = {"/admin/edit/accommodation", "/admin/edit/accommodation/"},
            method= RequestMethod.POST, params="action=save")
    public String saveAccommodation(@ModelAttribute("accommodationForm") AccommodationForm form,
                                 BindingResult bindingResult) {
        String resultView = "redirect:/admin/accommodation";
        try {
            accommodationService.saveOrUpdate(form.getAccommodation());
        }catch(Exception e){
            e.printStackTrace();
            resultView = "/admin/edit/accommodation_form";
        }
        return resultView;
    }

    @RequestMapping(value = {"/admin/edit/accommodation", "/admin/edit/accommodation/"},
            method=RequestMethod.POST, params="action=cancel")
    public String cancelAccommodation() {
        return "redirect:/admin/accommodation";
    }

}
