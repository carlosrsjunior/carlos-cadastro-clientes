package carlos.clientes.services;

import java.util.List;
import java.util.Optional;

import carlos.clientes.entity.Cliente;
import carlos.clientes.exceptions.CpfJaCadastradoException;

/**
 * Serviço para acesso ao repositório de Clientes.
 *  
 * @author Carlos
 *
 */
public interface ClienteService {

	/**
	 * Persiste um cliente na base de dados.
	 * 
	 * @param cliente
	 * @return Cliente
	 * @throws CpfJaCadastradoException 
	 */
	Cliente persistir(Cliente cliente) throws CpfJaCadastradoException;

	
	/**
	 * Busca e retorna clientes dado um nome.
	 * 
	 * @param cpf
	 * @return List<Cliente>
	 */
	List<Cliente> buscarPorNome(String nome);

	/**
	 * Busca e retorna um cliente dado um CPF.
	 * 
	 * @param cpf
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorCpf(String cpf);
	
	/**
	 * Busca e retorna um cliente dado um email.
	 * 
	 * @param email
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorEmail(String email);
	
	/**
	 * Busca e retorna um cliente por ID.
	 * 
	 * @param id
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarPorId(Long id);
	
	/**
	 * Busca e retorna clientes a partir de um exemplo.
	 * 
	 * @param cliente
	 * @return List<Cliente>
	 */
	List<Cliente> buscar(Cliente cliente); 

	/**
	 * Busca e retorna todos os clientes.
	 * 
	 * @return List<Cliente>
	 */
	List<Cliente> buscarTodos(); 

	
	void excluir(Long id);
	
	
}
