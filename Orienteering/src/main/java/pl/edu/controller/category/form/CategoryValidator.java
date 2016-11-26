package pl.edu.controller.category.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import pl.edu.service.category.ICategoryService;
import pl.edu.service.competition.ICompetitionInfoService;
import pl.edu.validator.CommonValidator;

public class CategoryValidator extends CommonValidator {

	@Autowired
    ICategoryService categoryService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CategoryForm.class);
	}

	@Override
	public void validateForm(Object target, Errors errors) {

        CategoryForm form = (CategoryForm) target;
		if (StringUtils.isBlank(form.getCategory().getName())) {
			errors.rejectValue("category.name", "category.name.cannot.be.null");
		}
	}
}
