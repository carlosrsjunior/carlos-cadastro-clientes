package carlos.clientes.util;


/**
 * Classe com funções utilitárias.
 * 
 * @author Carlos
 *
 */
public class Utils {

	/**
	 * Converte um valor String em um valor Enum correspondente.
	 * 
	 * @param str String a ser convertido.
	 * @param enumValues Valores do Enum correspondentes.
	 * @return Enum correspondente ou nulo, caso não seja possível converter.
	 */
	public static Enum converterStrToEnum(String str, Enum[] enumValues) {		
		Enum novoEnum = null;
		if (str != null && !str.equals("")) {
			for (int i = 0; i < enumValues.length; i++) {
				if (enumValues[i].name().equals(str)) {
					novoEnum = enumValues[i];
				}
			}				
		}		
		return novoEnum;
	}


	/**
	 * 	
	 * Converte um valor Enum em um valor String correspondente.
	 * 
	 * @param umEnum Um valor enum a ser convertido.
	 * @return string correspondente ou nulo, caso o enum também seja nulo.
	 */
	public static String converterEnumToStr(Enum umEnum) {
		String str = null;
		if (umEnum != null) {
			str = umEnum.name();
		}
		return str;
	}	

}
