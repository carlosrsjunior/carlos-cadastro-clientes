package carlos.clientes.view.validators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import carlos.clientes.view.validators.Validacao;

@SpringBootTest
@ActiveProfiles("test")
public class ValidacaoTest {
	

	private static final String cpfValido = "608.347.789-12";
	private static final String cpfInvalido = "608.347.789-11";
	private static final String cpfFormatoInvalido1 = "608.347.789.12";
	private static final String cpfFormatoInvalido2 = "608-347.789-12";
	private static final String cpfFormatoInvalido3 = "608.347-789-12";
	private static final String cpfTamanhoInvalido = "608.347-789";
	
	private static final String emailValido = "email@email.com";
	private static final String emailInvalido1 = "emailemail.com";
	private static final String emailInvalido2 = "email@email";
	
	
	@Test
	public void testValidarCpfValido() {
		assertTrue(Validacao.validarCPF(cpfValido));
	}

	@Test
	public void testValidarCpfInvalido() {
		assertFalse(Validacao.validarCPF(cpfInvalido));
	}

	@Test
	public void testValidarCpfFormatoInvalido() {
		assertFalse(Validacao.validarCPF(cpfFormatoInvalido1) 
				|| Validacao.validarCPF(cpfFormatoInvalido2) 
				|| Validacao.validarCPF(cpfFormatoInvalido3));
	}

	@Test
	public void testValidarCpfTamanhoInvalido() {
		assertFalse(Validacao.validarCPF(cpfTamanhoInvalido));
	}

	@Test
	public void testValidarEmail() {
		assertTrue(Validacao.validarEmail(emailValido));
	}


	@Test
	public void testValidarEmailInvalido() {
		assertFalse(Validacao.validarEmail(emailInvalido1) || Validacao.validarEmail(emailInvalido2));
	}

	
}
