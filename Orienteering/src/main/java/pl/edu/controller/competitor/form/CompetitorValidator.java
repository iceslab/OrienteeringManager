package pl.edu.controller.competitor.form;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import pl.edu.service.competitor.ICompetitorService;
import pl.edu.validator.CommonValidator;

public class CompetitorValidator extends CommonValidator {

	@Autowired
    ICompetitorService competitorService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(CompetitorForm.class);
	}

	@Override
	public void validateForm(Object target, Errors errors) {

        CompetitorForm form = (CompetitorForm) target;
		if (StringUtils.isBlank(form.getCompetitor().getName())) {
			errors.rejectValue("competitor.name", "competitor.name.cannot.be.null");
		}
        if (form.getCompetitor().getBirthDate() == null) {
            errors.rejectValue("competitor.birth_date", "competitor.birth_date.cannot.be.null");
        }
        if (form.getCompetitor().getGender() == null) {
            errors.rejectValue("competitor.gender", "competitor.gender.must.be.m.or.f");
        }
        if (form.getCompetitor().getCategoryId() == null) {
            errors.rejectValue("competitor.category_id", "competitor.category_id.cannot.be.null");
        }
	}
}
