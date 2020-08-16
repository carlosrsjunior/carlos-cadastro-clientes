package carlos.clientes.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferUtils.Matcher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import carlos.clientes.entity.Cliente;
import carlos.clientes.exceptions.CpfJaCadastradoException;
import carlos.clientes.repositories.ClienteRepository;
import carlos.clientes.services.ClienteService;


/**
 * Serviço para acesso ao repositório de Clientes.
 *  
 * @author Carlos
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {
	
	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente persistir(Cliente cliente) throws CpfJaCadastradoException {
		
		Optional<Cliente> clienteCadastrado = buscarPorCpf(cliente.getCpf());
		
		if (clienteCadastrado.isPresent() && 
				clienteCadastrado.get().getId() != cliente.getId()) {
			throw new CpfJaCadastradoException("CPF já cadastrado.");
		} 

		log.info("Persistindo cliente: {}", cliente);
		if (cliente.getId() != null && cliente.getId() != 0) {
			Cliente c = this.clienteRepository.findById(cliente.getId()).get();
			cliente.setDataCriacao(c.getDataCriacao());
		}
		return this.clienteRepository.save(cliente);
	}

	public List<Cliente> buscarPorNome(String nome) {
		log.info("Buscando cliente pelo nome {}", nome);
		return this.clienteRepository.findByNomeLike("%"+nome+"%");
	}

	public Optional<Cliente> buscarPorCpf(String cpf) {
		log.info("Buscando cliente pelo CPF {}", cpf);
		return Optional.ofNullable(this.clienteRepository.findByCpf(cpf));
	}
	
	public Optional<Cliente> buscarPorEmail(String email) {
		log.info("Buscando cliente pelo email {}", email);
		return Optional.ofNullable(this.clienteRepository.findByEmail(email));
	}
	
	public Optional<Cliente> buscarPorId(Long id) {
		log.info("Buscando cliente pelo ID {}", id);
		return this.clienteRepository.findById(id);
	}

	public List<Cliente> buscar(Cliente cliente) {
		log.info("Buscando clientes");
		ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("nome", match -> match.contains()).withIgnoreCase();
		Example<Cliente> example = Example.of(cliente, matcher);
        return (List<Cliente>) this.clienteRepository.findAll(example);
	}

	public List<Cliente> buscarTodos() {
		log.info("Buscando todos os clientes");
        return (List<Cliente>) this.clienteRepository.findAll();
	}

	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}

}
