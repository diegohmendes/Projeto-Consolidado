package br.com.fiescfrotas.util;

public class MensagemUtil {
	
	public <T> String cadastrado(T entity) {
		return entity.getClass().getSimpleName() + " cadastrado com sucesso";
	}
	
	public <T> String editado(T entity) {
		return entity.getClass().getSimpleName() + " editado com sucesso";
	}
	
	public <T> String cancelado(T entity) {
		return entity.getClass().getSimpleName() + " cancelado com sucesso";
	}
	
	public <T> String removido(T entity) {
		return entity.getClass().getSimpleName() + " removido com sucesso";
	}
	
	public String codigoInvalido(String campo) {
		return "Desculpe, parece que o valor do " + campo + " é inválido para esta ação";
	}
	
	public String campoVazio(String campo) {
		return "Desculpe, parece que o campo " + campo + " não foi informado";
	}

	public String dataMenorQueAtual(String campo) {
		return "Desculpe, parece que a data informa no campo " + campo + " é menor ou igual que a data atual";
	}

	public String loginDuplicado(String login) {
		return "Desculpe, parece que o login " + login + " pertence à uma conta existente";
	}
	
	public String cnhDuplicado(long cnh) {
		return "Desculpe, parece que o cnh " + cnh + " pertence à uma conta existente";
	}
	
	public String emailDuplicado(String email) {
		return "Desculpe, parece que o email " + email + " pertence à uma conta existente";
	}
	
	public String matriculaDuplicado(int matricula) {
		return "Desculpe, parece que o matricula " + matricula + " pertence à uma conta existente";
	}
	
	public String usuarioCancelado(long codigo) {
		return "Desculpe, o usuário do código " + codigo + " está cancelado";
	}

	public String kmInvalido(float quilometragem) {
		return "Desculpe, a quilometragem informada " + quilometragem + " não é válida";
	}

	public String motorizacaoInvalido(float motorizacao) {
		return "Desculpe, a motorização informada " + motorizacao + " não é válida";
	}

	public String anoInvalido(int ano) {
		return "Desculpe, o ano do veículo informado " + ano + " é inválido";
	}

	public String patrimonioInvalido(int patrimonio) {
		return "Desculpe, o patrimonio informado " + patrimonio + " é inválido";
	}

	public String placaDuplicada(String placa) {
		return "Desculpe, parece que a placa " + placa + " pertence à um veículo existente";
	}

	public String patrimonioDuplicado(int patrimonio) {
		return "Desculpe, parece que o patrimonio " + patrimonio + " pertence à um veículo existente";
	}

	public String placaInvalida(String placa) {
		return "Desculpe, parece que a placa " + placa + " informada é inválida";
	}

	public String autorizacaoInvalida(String usuario) {
		return "Desculpe, você não possui permissão para ativar o usuário " + usuario + " pois não são da mesma unidade";
	}

}