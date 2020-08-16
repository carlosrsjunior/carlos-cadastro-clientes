package carlos.clientes.view.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


/**
 * Validator para valores de CPF;
 * 
 * @author Carlos
 *
 */
public class CPFValidator implements javax.faces.validator.Validator {

	public void validate(FacesContext facesContext, UIComponent arg1,
			Object value) throws ValidatorException {
		String inputValue = (String) value;
		
		if (!Validacao.validarCPF(inputValue)) {
			FacesMessage facesMessage = new FacesMessage("CPF inválido",
					"CPF inválido");
			throw new ValidatorException(facesMessage);
		}
	}
}
