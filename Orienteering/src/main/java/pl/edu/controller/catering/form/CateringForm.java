package pl.edu.controller.catering.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import pl.edu.model.catering.Catering;
import pl.edu.mvc.AbstractForm;

public class CateringForm extends AbstractForm {

	private static final long serialVersionUID = 1574688377467056255L;

	@Getter @Setter
	private Catering catering;

	@Getter @Setter
	private String[] options;

    public CateringForm(){
    }

	@Override
	public String getID() {
		return new Md5PasswordEncoder().encodePassword(catering.getId() + "", "sdfNOghELOjklkjuhLOGINygtfrLOLrtgyhjk");
	}

}
