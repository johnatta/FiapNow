package br.com.fiap.mb;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "validaEmail")
public class ValidatorLogin implements Validator {
	public void validate(FacesContext context, UIComponent component,
			Object value) {
		String valor = value.toString();
		if (!valor.matches("^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$")) {
			throw new ValidatorException(new FacesMessage(
					"Insira um email válido"));
		}
	}
}
