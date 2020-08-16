package carlos.clientes.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


/**
 * Validator para valores de email;
 * 
 * @author Carlos
 *
 */
public class EmailValidator implements javax.faces.validator.Validator {
	public void validate(FacesContext facesContext, UIComponent arg1,
			Object value) throws ValidatorException {
		String inputValue = (String) value;

		if (!Validacao.validarEmail(inputValue)) {
			FacesMessage facesMessage = new FacesMessage("E-mail inválido",
					"E-mail inválido");
			throw new ValidatorException(facesMessage);
		}
	}
}
