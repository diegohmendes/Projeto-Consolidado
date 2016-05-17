package br.com.fiescfrotas.validacao;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.fiescfrotas.exception.CampoEmBrancoException;
import br.com.fiescfrotas.exception.CodigoException;
import br.com.fiescfrotas.exception.DataException;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.exception.UsuarioException;
import br.com.fiescfrotas.modelo.Usuario;
import br.com.fiescfrotas.util.MensagemUtil;

public class UsuarioValidar {

	static Logger log = Logger.getLogger(UsuarioValidar.class);
	private static MensagemUtil msg = new MensagemUtil();
	
	public static void validarUsuarioRN(Usuario usuario, List<Usuario> listaUsuarios) throws FrotasException {
		for (Usuario usuarioCadastrado : listaUsuarios) {

			log.trace("Iniciando validação dos campos obrigatórios");
			
			log.trace("usuário código: " + usuario.getCodigo() + " usuário cadastrado código: " + usuarioCadastrado.getCodigo());
			log.trace("usuário cnh: " + usuario.getCnh() + " usuário cadastrado cnh: " + usuarioCadastrado.getCnh());
			log.trace("usuário email: " + usuario.getEmail() + " usuário cadastrado email: " + usuarioCadastrado.getEmail());
			log.trace("usuário login: " + usuario.getLogin() + " usuário cadastrado login: " + usuarioCadastrado.getLogin());
			log.trace("usuário matricula: " + usuario.getMatricula() + " usuário cadastrado matricula: " + usuarioCadastrado.getMatricula());
			
			if (usuario.getCnh() == usuarioCadastrado.getCnh()) {
				throw new UsuarioException(msg.cnhDuplicado(usuario.getCnh()));
			} else if (usuario.getEmail().equals(usuarioCadastrado.getEmail())) {
				throw new UsuarioException(msg.emailDuplicado(usuario.getEmail()));
			} else if (usuario.getLogin().equals(usuarioCadastrado.getLogin())) {
				throw new UsuarioException(msg.loginDuplicado(usuario.getLogin()));
			} else if (usuario.getMatricula() == usuarioCadastrado.getMatricula()) {
				throw new UsuarioException(msg.matriculaDuplicado(usuario.getMatricula()));
			}
		}
	}

	public void validarUsuario(int codigo, Usuario usuario) throws FrotasException {
		
		if (codigo == 1) {
			if (!ValidarObjeto.validarCodigo(usuario.getCodigo())) {
				throw new CodigoException(msg.codigoInvalido("codigo"));
			}
		} else if (codigo == 2) {
			if (ValidarObjeto.validarCodigo(usuario.getCodigo())) {
				throw new CodigoException(msg.codigoInvalido("codigo"));
			}
		} else if (usuario.getNome() == null || ValidarObjeto.validarString(usuario.getNome())) {
			throw new CampoEmBrancoException(msg.campoVazio("nome"));
		} else if (usuario.getEmail() == null || ValidarObjeto.validarString(usuario.getEmail())) {
			throw new CampoEmBrancoException(msg.campoVazio("e-mail"));
		} else if (usuario.getLogin() == null || ValidarObjeto.validarString(usuario.getLogin())) {
			throw new CampoEmBrancoException(msg.campoVazio("login"));
		} else if (usuario.getSenha() == null || ValidarObjeto.validarString(usuario.getSenha())) {
			throw new CampoEmBrancoException(msg.campoVazio("senha"));
		} else if (ValidarObjeto.validarCodigo(usuario.getCnh())) {
			throw new CodigoException(msg.codigoInvalido("cnh"));
		} else if (ValidarObjeto.validarCodigo(usuario.getTipoCNH())) {
			throw new CodigoException(msg.codigoInvalido("tipo cnh"));
		} else if (usuario.getValidadeCNH() == null || !ValidarObjeto.validarDataAtual(usuario.getValidadeCNH())) {
			throw new DataException(msg.dataMenorQueAtual("validade cnh"));
		} else if (ValidarObjeto.validarCodigo(usuario.getCodUnidade())) {
			throw new CodigoException(msg.codigoInvalido("codigo unidade"));
		} else if (usuario.getSetor() == null || ValidarObjeto.validarString(usuario.getSetor())) {
			throw new CodigoException(msg.campoVazio("setor"));
		} else if (ValidarObjeto.validarCodigo(usuario.getMatricula())) {
			throw new CodigoException(msg.codigoInvalido("matricula"));
		} else if (ValidarObjeto.validarCodigo(usuario.getCodPerfil())) {
			throw new CodigoException(msg.codigoInvalido("perfil"));
		} else if (ValidarObjeto.validarCodigo(usuario.getStatus())) {
			throw new CodigoException(msg.codigoInvalido("status"));
		}
	}

}