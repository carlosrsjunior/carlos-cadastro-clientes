package carlos.clientes.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import carlos.clientes.entity.Cliente;
import carlos.clientes.exceptions.CpfJaCadastradoException;
import carlos.clientes.repositories.ClienteRepository;



@SpringBootTest
@ActiveProfiles("test")
public class ClienteServiceTest {
	
	@Autowired
	private ClienteService clienteService;

	@Autowired
	public ClienteRepository clienteRepository;

	private static final String NOME = "Fulano";	
	private static final String NOME1 = "Fulano da Silva";
	private static final String NOME2 = "Fulano Ferreira";;
	private static final String CPF1 = "123.456.789-01";
	private static final String CPF2 = "987.654.321-01";
	private static final String EMAIL1 = "email1@email.com";
	private static final String EMAIL2 = "email2@email.com";
	
	private static final String NOME_NOVO = "Fulano";
	private static final String CPF_NOVO = "135.790.246-90";
	private static final String CPF_REPETIDO = CPF1;
	
	private Cliente cliente;

	
	@BeforeEach
	public void setUp() throws Exception {
		Cliente cliente = new Cliente();
		cliente.setNome(NOME1);
		cliente.setCpf(CPF1);
		cliente.setDataNascimento(new Date());
		cliente.setEmail(EMAIL1);
		
		clienteRepository.save(cliente);
		
		cliente = new Cliente();
		cliente.setNome(NOME2);
		cliente.setCpf(CPF2);
		cliente.setDataNascimento(new Date());
		cliente.setEmail(EMAIL2);
		
		clienteRepository.save(cliente);

		cliente = new Cliente();
		cliente.setNome("Teste Data");
		cliente.setCpf("321321321");
		cliente.setDataNascimento(new Date());
		
		clienteRepository.save(cliente);

		this.cliente = cliente;
	}
	
	
	@AfterEach
    public final void tearDown() { 
		this.clienteRepository.deleteAll();
	}

	
	@Test
	public void testBuscarPorId() {
		Long id = cliente.getId();		
		Optional<Cliente> cliente = this.clienteService.buscarPorId(id);
		assertEquals(id, cliente.get().getId());
	}

	@Test
	public void testExcluir() {
		Optional<Cliente> cliente = this.clienteService.buscarPorCpf(CPF1);
		assertTrue(cliente.isPresent());

		this.clienteService.excluir(cliente.get().getId());
		cliente = this.clienteService.buscarPorCpf(CPF1);
		assertFalse(cliente.isPresent());
	}

	
	@Test
	public void testBuscarTodos() {
		List<Cliente> clientes = this.clienteService.buscarTodos();
		assertEquals(3, clientes.size());
	}

	@Test
	public void testBuscarPorParteDoNome() {
		Cliente cliente = new Cliente();
		cliente.setNome(NOME);
		List<Cliente> clientes = this.clienteService.buscar(cliente);

		assertEquals(2, clientes.size());
	}

	@Test
	public void testBuscarPorParteDoNomeECpfExato() {
		Cliente cliente = new Cliente();
		cliente.setNome(NOME1);
		cliente.setCpf(CPF1);
		List<Cliente> clientes = this.clienteService.buscar(cliente);

		assertEquals(1, clientes.size());
	}

	@Test
	public void testBuscarPorNome() {
		List<Cliente> clientes = this.clienteService.buscarPorNome(NOME1);
		assertEquals(1, clientes.size());
	}


	@Test
	public void testBuscarPorCpf() {
		Optional<Cliente> cliente = this.clienteService.buscarPorCpf(CPF1);
		assertTrue(cliente.isPresent());
		assertEquals(CPF1, cliente.get().getCpf());
	}

	@Test
	public void testBuscarPorEmail() {
		Optional<Cliente> cliente = this.clienteService.buscarPorEmail(EMAIL1);
		assertTrue(cliente.isPresent());
		assertEquals(EMAIL1, cliente.get().getEmail());
	}

	@Test
	public void testPersistirSucesso() {
		Cliente cliente = new Cliente();
		cliente.setNome(NOME_NOVO);
		cliente.setCpf(CPF_NOVO);
		cliente.setDataNascimento(new Date());
		
		assertTrue(cliente.getId() == null);
		
		try {
			Cliente clientePersistido = this.clienteService.persistir(cliente);
			assertTrue(clientePersistido.getId() != null);
		} catch (Exception e) {}				
	}

	@Test
	public void testPersistirCpfExistente() {
		Cliente cliente = new Cliente();
		cliente.setNome(NOME_NOVO);
		cliente.setCpf(CPF_REPETIDO);
		cliente.setDataNascimento(new Date());
		
		assertTrue(cliente.getId() == null);
		
		try {
			Cliente clientePersistido = this.clienteService.persistir(cliente);
		} catch (Exception e) {
			assertTrue(e instanceof CpfJaCadastradoException);	
		}						
	}

}
