package carlos.clientes.exceptions;


/**
 * Exceção que representa a existência de um outro cpf igual já existente.
 * 
 * @author Carlos
 *
 */
public class CpfJaCadastradoException extends Exception {


	/**
	 * UID
	 */
	private static final long serialVersionUID = 1536571701566897645L;

	/**
	 * Construtor da classe
	 * 
	 * @param mensagem
	 */
	public CpfJaCadastradoException(String mensagem) {
		super(mensagem);
	}
}
