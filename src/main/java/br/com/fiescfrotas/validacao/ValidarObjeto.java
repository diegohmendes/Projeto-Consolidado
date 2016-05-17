package br.com.fiescfrotas.validacao;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class ValidarObjeto {
	
	private static Logger log = Logger.getLogger(ValidarObjeto.class);

	public static boolean validarCodigo(long codigo) {
		return (codigo == 0 ? true : false);
	}

	public static boolean validarString(String texto) {
		return (texto.equals("") ? true : false);
	}

	public static boolean validarDataAtual(Date data) {
		
		Calendar dataAtual = Calendar.getInstance();
	    dataAtual.setTime( new Date() );
	    dataAtual.set(Calendar.HOUR_OF_DAY, 0);
	    dataAtual.set(Calendar.MINUTE, 0);
	    dataAtual.set(Calendar.SECOND, 0);
	    dataAtual.set(Calendar.MILLISECOND, 0);
	    
	    Calendar dataValidade = Calendar.getInstance();
	    dataValidade.setTime( data );
	    dataValidade.set(Calendar.HOUR_OF_DAY, 0);
	    dataValidade.set(Calendar.MINUTE, 0);
	    dataValidade.set(Calendar.SECOND, 0);
	    dataValidade.set(Calendar.MILLISECOND, 0);

		log.trace("Data validade original: " + data + " Data atual no servidor ajustada: " + dataAtual.getTime() + " Data validade ajustada: " + dataValidade.getTime());
		
	    return (dataAtual.getTime().compareTo(dataValidade.getTime()) < 0 ? true : false);
	}

}