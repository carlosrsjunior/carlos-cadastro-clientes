package carlos.clientes.view.validators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Calsse auxiliar com funções de validação
 * 
 * @author Carlos
 *
 */
public class Validacao {


	/**
	 * Valida formato de um cpf, incluindo tamanho, caracteres e dígito verificador.
	 * 
	 * @param strCpf
	 * @return boolean
	 */
	public static boolean validarCPF(String strCpf) {


		int iDigito1Aux = 0, iDigito2Aux = 0, iDigitoCPF;
		int iDigito1 = 0, iDigito2 = 0, iRestoDivisao = 0;
		String strDigitoVerificador, strDigitoResultado;
		
		if (!strCpf.substring(0, 1).equals("")) {
			
			if (strCpf.length() != 14 || strCpf.charAt(3) != '.' || strCpf.charAt(7) != '.' ||
					strCpf.charAt(11) != '-') {
				return false;
			}

			try {
				strCpf = strCpf.replace('.', ' ');
				strCpf = strCpf.replace('-', ' ');
				strCpf = strCpf.replaceAll(" ", "");
				for (int iCont = 1; iCont < strCpf.length() - 1; iCont++) {
					iDigitoCPF = Integer.valueOf(
							strCpf.substring(iCont - 1, iCont)).intValue();
					iDigito1Aux = iDigito1Aux + (11 - iCont) * iDigitoCPF;
					iDigito2Aux = iDigito2Aux + (12 - iCont) * iDigitoCPF;
				}
				iRestoDivisao = (iDigito1Aux % 11);
				if (iRestoDivisao < 2) {
					iDigito1 = 0;
				} else {
					iDigito1 = 11 - iRestoDivisao;
				}
				iDigito2Aux += 2 * iDigito1;
				iRestoDivisao = (iDigito2Aux % 11);
				if (iRestoDivisao < 2) {
					iDigito2 = 0;
				} else {
					iDigito2 = 11 - iRestoDivisao;
				}
				strDigitoVerificador = strCpf.substring(strCpf.length() - 2,
						strCpf.length());
				strDigitoResultado = String.valueOf(iDigito1)
						+ String.valueOf(iDigito2);
				return strDigitoVerificador.equals(strDigitoResultado);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Valida formato de email
	 * 
	 * @param email
	 * @return boolean
	 */
	public static boolean validarEmail(String email) {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		boolean matchFound = m.matches();
		return matchFound;
	}

}
