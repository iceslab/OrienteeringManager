package pl.edu.controller.category.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.controller.BaseController;
import pl.edu.controller.category.form.CategoryForm;
import pl.edu.model.category.Category;
import pl.edu.repository.category.Categories;
import pl.edu.service.category.ICategoryService;

import java.util.List;

/**
 * Created by bartosz on 23.04.16.
 */
@Controller("adminCategoryController")
public class AdminCategoryController extends BaseController {

    @Autowired
    ICategoryService categoryService;

    @ModelAttribute("categories")
    public List<Category> categoryList(){
        List<Category> categoryList = categoryService.list(Categories.findAll());
        return categoryList;
    }

    @ModelAttribute("categoryForm")
    CategoryForm categoryForm(){
        return new CategoryForm();
    }

    @RequestMapping(value = {"/admin/category", "/admin/category/"})
    public String category(){
        return "admin/category";
    }

    @RequestMapping(value = {"/admin/edit/category", "/admin/edit/category/"})
    public String categoryRegister(@ModelAttribute("categoryForm") CategoryForm form,
                                        BindingResult bindingResult){
        return "admin/edit/category_form";
    }

    @RequestMapping(value = {"/admin/edit/category", "/admin/edit/category/"},
            method= RequestMethod.POST, params="action=save")
    public String saveCategoryInfo(@ModelAttribute("categoryForm") CategoryForm form,
                                    BindingResult bindingResult) {
        String resultView = "redirect:/admin/category";
        try {
            categoryService.saveOrUpdate(form.getCategory());
        }catch(Exception e){
            e.printStackTrace();
            resultView = "/admin/edit/category_form";
        }
        return resultView;
    }

    @RequestMapping(value = {"/admin/edit/category", "/admin/edit/category/"},
            method=RequestMethod.POST, params="action=cancel")
    public String cancelCategoryInfo() {
        return "redirect:/admin/category";
    }
}
