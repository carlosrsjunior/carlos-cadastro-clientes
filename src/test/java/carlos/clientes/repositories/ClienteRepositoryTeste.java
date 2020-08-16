package carlos.clientes.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import carlos.clientes.entity.Cliente;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteRepositoryTeste {

	@Autowired
	public ClienteRepository clienteRepository;
	
	private static final String NOME = "Cliente";
	private static final String NOME1 = NOME + 1;
	private static final String NOME2 = NOME + 2;
	private static final String CPF1 = "123.456.789-01";
	private static final String CPF2 = "987.654.321-01";
	private static final String EMAIL1 = "email1@email.com";
	private static final String EMAIL2 = "email2@email.com";

	
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

	}
	
	@AfterEach
    public final void tearDown() { 
		this.clienteRepository.deleteAll();
	}

	@Test
	public void testFindByNome() {
		List<Cliente> clientes = this.clienteRepository.findByNome(NOME1);
		assertEquals(1, clientes.size());
	}

	@Test
	public void testFindByNomeLike() {
		List<Cliente> clientes = this.clienteRepository.findByNomeLike("%"+NOME+"%");	
		assertEquals(2, clientes.size());
	}

	@Test
	public void testFindByCpf() {
		Cliente cliente = this.clienteRepository.findByCpf(CPF1);
		assertEquals(CPF1, cliente.getCpf());
	}

	@Test
	public void testFindByEmail() {
		Cliente cliente = this.clienteRepository.findByEmail(EMAIL1);	
		assertEquals(EMAIL1, cliente.getEmail());
	}

}
