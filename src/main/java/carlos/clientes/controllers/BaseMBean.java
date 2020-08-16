package carlos.clientes.controllers;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Superclasse para o(s) managed bean(s) da aplicação 
 * 
 * @author Carlos
 *
 */
public class BaseMBean {

	/**
	 * Adiciona uma mensagem de erro no contexto.
	 * 
	 * @param mensagem
	 */
	protected void addError(String mensagem) {
		addError(null, mensagem);
	}	

	/**
	 * Adiciona uma mensagem de erro no contexto para um determinado elemento de tela.
	 * 
	 * @param id id do elemento.
	 * @param mensagem mensagem de erro
	 */
	protected void addError(String id, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + mensagem, mensagem);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(id, facesMessage);

	}	

	/**
	 * Adiciona uma mensagem de sucesso no contexto
	 * 
	 * @param mensagem
	 */
	protected void addSuccess(String mensagem) {		
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, mensagem);		
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, facesMessage);
	}	
	
	/**
	 * Adiciona um atributo com seu valor no Request.
	 * 
	 * @param attributeName
	 * @param object
	 */
	protected void putIntoRequest(String attributeName, Object object) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getRequestMap().put(attributeName, object);
	}
	
	
	/**
	 * Adiciona um atributo com seu valor na sessão.
	 * 
	 * @param attributeName
	 * @param object
	 */
	protected void putIntoSession(String attributeName, Object object) {

		FacesContext context = FacesContext.getCurrentInstance();

		if(object == null){
			context.getExternalContext().getSessionMap().remove(attributeName);
		} else {
			context.getExternalContext().getSessionMap().put(attributeName, object);
		}
	}

	
}
