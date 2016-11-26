package pl.edu.controller.category.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import pl.edu.model.category.Category;
import pl.edu.model.competition.CompetitionInfo;
import pl.edu.mvc.AbstractForm;

public class CategoryForm extends AbstractForm {

	private static final long serialVersionUID = 1574688377467056255L;

	@Getter @Setter
	private Category category;

    public CategoryForm(){
    }

	@Override
	public String getID() {
		return new Md5PasswordEncoder().encodePassword(category.getId() + "", "sdfNOghELOjklkjuhLOGINygtfrLOLrtgyhjk");
	}

}
