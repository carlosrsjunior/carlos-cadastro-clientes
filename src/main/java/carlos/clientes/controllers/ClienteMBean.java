package carlos.clientes.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import carlos.clientes.entity.Cliente;
import carlos.clientes.enums.SexoEnum;
import carlos.clientes.exceptions.CpfJaCadastradoException;
import carlos.clientes.services.ClienteService;
import carlos.clientes.util.Utils;


/**
 * Managed Bean para gerenciar a navegação de cadastro, pesquisa, alteração e exclusão de cliente
 * 
 * @author Carlos
 *
 */
@Controller
@Scope("request")
public class ClienteMBean extends BaseMBean {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteMBean.class);

	@Autowired
	private ClienteService clienteService;
	
	private Cliente cliente = new Cliente();
	
	private Long clienteId; 
	
	private String sexo;	

	private List<Cliente> clientes;
	
	public String inicio() {
		cliente = new Cliente();
		List<Cliente> clientesCadastrados = clienteService.buscarTodos();
		if (clientesCadastrados != null) {
			clientes = clientesCadastrados;
		}
		return "pesquisar";
	}
	

	/**
	 * Redireciona para a tela de cadastro de um novo cliente.
	 * 
	 * @return
	 */
	public String cadastrar() {		
		cliente = new Cliente();
		putIntoSession("titulo", "Novo Cliente");
		return "cadastrar";
	}	

	
	/**
	 * Redireciona para a tela de edição de um cliente.
	 * 
	 * @return
	 */
	public String editar() {
		
		Optional<Cliente> clienteCadastrado = clienteService.buscarPorId(clienteId);

		if (!clienteCadastrado.isPresent()) {
			addError(null, "Cliente inexistente.");
			return pesquisar();
		} 
				
		cliente = clienteCadastrado.get();
		sexo = Utils.converterEnumToStr(cliente.getSexo());
		putIntoSession("titulo", "Editar Cliente");
		return "cadastrar";
	}

	/**
	 * Salva os dados de um novo ou atualiza os dados de um cliente.
	 * 
	 * @return
	 */
	public String salvar() {			
		try {
			SexoEnum sexoEnum = (SexoEnum) Utils.converterStrToEnum(sexo, SexoEnum.values());
			cliente.setSexo(sexoEnum);
			
			if (ehNovoCliente(cliente )) {
				clienteService.persistir(cliente);
				log.info("Cliente salvo: {}", cliente.getId());
				addSuccess("Cliente cadastro com sucesso. Preencha para adicionar outro.");
				cliente = null;
				sexo = null;
			} else {
				clienteService.persistir(cliente);
				log.info("Cliente atualizado: {}", cliente.getId());
				addSuccess("Cliente atualizado com sucesso.");							
			}
		} catch(CpfJaCadastradoException e) {
			addError("F:Cpf", "CPF já cadastrado.");
		} catch(Exception e) {
			addError(e.getMessage());
		}

		return null;
	}
	
	
	/**
	 * Realiza a pesquisa de clientes baseada no filtro da tela de pesquisa.
	 * 
	 * @return
	 */
	public String pesquisar() {
		
		clientes = new ArrayList<Cliente>();
		if (!validacaoFormulario()) {
			return inicio();
		} else {
			Cliente filtro = normalizarFiltro(cliente);
			List<Cliente> clientesCadastrados = clienteService.buscar(filtro);
			if (clientesCadastrados != null) {
				clientes = clientesCadastrados;
			}
		}
		return "pesquisar";
	}

	
	/**
	 * Remove um cliente do cadastro.
	 * 
	 * @return
	 */
	public String excluir() {

		try {			
			clienteService.excluir(this.clienteId);			
		} catch(Exception e) {
			addError(e.getMessage());
		}
		return pesquisar();
	}

	private boolean ehNovoCliente(Cliente cliente) {
		return cliente.getId() == null || cliente.getId() == 0;
	}


	private Cliente normalizarFiltro(Cliente cliente) {
		Cliente filtro = new Cliente();
		
		if ("".equals(cliente.getNome())) {
			filtro.setNome(null);
		} else {
			filtro.setNome(cliente.getNome());
		}
		
		if ("".equals(cliente.getCpf())) {
			filtro.setCpf(null);
		} else {
			filtro.setCpf(cliente.getCpf());
		}
		
		return filtro;
	}


	private boolean validacaoFormulario() {
		return (cliente.getNome() != null && !cliente.getNome().equals("")) ||
				(cliente.getCpf() != null && !cliente.getCpf().equals(""));
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Long getClienteId() {
		return clienteId;
	}


	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}


	public List<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
		
}
