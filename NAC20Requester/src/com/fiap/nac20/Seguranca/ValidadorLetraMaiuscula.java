package com.fiap.nac20.Seguranca;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "comecaComMaiuscula")
public class ValidadorLetraMaiuscula implements Validator {
	public void validate(FacesContext context, UIComponent component,
			Object value) {
		String valor = value.toString();
		if (!valor.matches("[A-Z].*")) {
			throw new ValidatorException(new FacesMessage(
					"Começar com maiúscula"));
		}
	}
}