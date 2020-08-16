package carlos.clientes.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import carlos.clientes.enums.SexoEnum;

@SpringBootTest
@ActiveProfiles("test")
public class UtilsTest {

	private String strValido = "MASCULINO";
	private String strInvalido = "INDEFINIDO";
	private Enum umEnum = SexoEnum.MASCULINO;
	
	@Test
	public void testConverterStrToEnumSucesso() {
		Enum umEnum = Utils.converterStrToEnum(strValido, SexoEnum.values());
		assertNotNull(umEnum);
	}
	
	@Test
	public void testConverterStrToEnumStringInvalido() {
		Enum umEnum = Utils.converterStrToEnum(strInvalido, SexoEnum.values());
		assertNull(umEnum);
	}

	@Test
	public void testConverterStrToEnumStringNulo() {
		Enum umEnum = Utils.converterStrToEnum(null, SexoEnum.values());
		assertNull(umEnum);
	}

	@Test
	public void testConverterEnumToStrSucesso() {
		String str = Utils.converterEnumToStr(umEnum);
		assertEquals(umEnum.name(), str);
	}

	@Test
	public void testConverterEnumToStrEnumNulo() {
		String str = Utils.converterEnumToStr(null);
		assertNull(str);
	}

}
