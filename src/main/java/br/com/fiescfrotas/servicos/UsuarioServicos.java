package br.com.fiescfrotas.servicos;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.fiescfrotas.constants.UsuarioConstants;
import br.com.fiescfrotas.dao.UsuarioDAO;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.interfaces.IServicos;
import br.com.fiescfrotas.modelo.Usuario;
import br.com.fiescfrotas.util.MailUtil;
import br.com.fiescfrotas.util.MensagemUtil;
import br.com.fiescfrotas.validacao.UsuarioValidar;

public class UsuarioServicos implements IServicos<Usuario> {

	static Logger log = Logger.getLogger(UsuarioServicos.class);
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private MensagemUtil msg = new MensagemUtil();
	UsuarioValidar validar = new UsuarioValidar();

	/**
	 * Método para inserir usuario.
	 * 
	 * @param usuario
	 *            Usuario - Objeto usuario
	 * @return void
	 * @throws FrotasException 
	 * @throws StringException 
	 */
	@Override
	public void inserir(Usuario usuario) throws FrotasException {
		log.debug("Iniciado validação do usuário");
		log.trace(usuario.toString());
		
		validar.validarUsuario(1, usuario);

		List<Usuario> listaUsuarios = usuarioDAO.buscarTodosUnicos(usuario.getCodigo(), usuario.getCnh(),
				usuario.getEmail(), usuario.getLogin(), usuario.getMatricula());
		
		log.trace("Lista contém " + listaUsuarios.size());

		if (listaUsuarios.isEmpty()) {
			log.debug("Iniciando persistência do usuário");
			usuarioDAO.inserir(usuario);

			enviarEmailUsuario(UsuarioConstants.AGUARDANDO_APROVACAO, usuario);

		} else {

			UsuarioValidar.validarUsuarioRN(usuario, listaUsuarios);
		}
	}

	/**
	 * Método para buscar todos os usuarios - Sem filtro.
	 * 
	 * @return List<Usuario> - Uma lista de usuarios encontrados no banco.
	 */
	public List<Usuario> buscarTodos() throws FrotasException {

		List<Usuario> listaUsuarios = usuarioDAO.buscarTodosUsuariosAtivos();
		
		if (listaUsuarios.isEmpty()) {
			throw new FrotasException("OK");
		} else {
			return listaUsuarios;
		}
	}
	
	/**
	 * Método para buscar um usuario específico.
	 * 
	 * @param codigo
	 *            Id do usuario
	 * @return Objeto usuario
	 * @throws FrotasException
	 */
	public Usuario buscarPorCodigo(long codigo) throws FrotasException {
		Usuario usuario = usuarioDAO.buscarPorCodigo(codigo);
		
		if (usuario == null) {
			throw new FrotasException(msg.codigoInvalido("usuário"));
		} else if (usuario.getStatus() != UsuarioConstants.ATIVO) {
			throw new FrotasException(msg.usuarioCancelado(codigo));
		} else {
			return usuario;
		}
	}

	/**
	 * Método para atualizar usuario.
	 * 
	 * @param codigo
	 *            Usuario - Objeto usuario
	 * @return void
	 * @throws FrotasException 
	 */
	public void atualizar(long codigo) throws FrotasException {
		log.debug("Iniciando atualização do usuário");
		Usuario usuario = usuarioDAO.buscarPorCodigo(codigo);
		
		if (usuario == null) {
			throw new FrotasException("Usuario não existente");
		} else if (usuario.getStatus() != UsuarioConstants.ATIVO){
			throw new FrotasException("Usuario já cancelado");
		} else {
			usuario.setStatus(UsuarioConstants.INATIVO);
			log.debug("Iniciando persistência do usuário");
			usuarioDAO.atualizar(usuario);
		}
	}
	
	/**
	 * Método para atualizar usuario.
	 * 
	 * @param usuario
	 *            Usuario - Objeto usuario
	 * @return void
	 */
	public void atualizar(Usuario usuario) throws FrotasException {
		log.debug("Iniciando validação do usuário");
		log.trace(usuario.toString());
		
		validar.validarUsuario(2, usuario);
		
		List<Usuario> listaUsuarios = usuarioDAO.buscarTodosUnicos(usuario.getCodigo(), usuario.getCnh(),
				usuario.getEmail(), usuario.getLogin(), usuario.getMatricula());
		log.trace("Lista contém " + listaUsuarios.size());

		if (!listaUsuarios.isEmpty()) {
			UsuarioValidar.validarUsuarioRN(usuario, listaUsuarios);
		}
		
		log.debug("Iniciando persistência do usuário");
		usuarioDAO.atualizar(usuario);

		log.trace("Efetuando envio de e-mail para o usuário " + usuario.getEmail());
		MailUtil.sentEmail("Cadastro de Usuário", "Seu usuário foi atualizado com sucesso.", usuario.getEmail());
		
	}

	/**
	 * @param codPerfil
	 * @return
	 */
	public boolean buscarAutorizacaoPagina(long codPerfil, String uri) {
		return usuarioDAO.buscarAutorizacaoPerfil(codPerfil, uri);
	}

	/**
	 * @return
	 * @throws FrotasException
	 */
	public List<Usuario> buscarUsuariosSemAprovacao(long codigoAutorizador) throws FrotasException {

		Usuario usuarioAutorizador = usuarioDAO.buscarPorCodigo(codigoAutorizador);
		if(usuarioAutorizador == null) {
			throw new FrotasException("Usuário sem permissão");
		}
		List<Usuario> listaUsuarios = usuarioDAO.buscarTodosUsuariosParaAprovacao(usuarioAutorizador.getCodUnidade());
		if (listaUsuarios.isEmpty()) {
			throw new FrotasException("Nenhum usuário para aprovação");
		} else {
			return listaUsuarios;
		}
	}

	/**
	 * @param status
	 * @param codigoUsuario
	 * @param codigoAutorizador
	 * @throws FrotasException
	 */
	public void alterarStatus(int status, long codigoUsuario, long codigoAutorizador) throws FrotasException {
		log.debug("Iniciando autorização do usuário");
		
		Usuario usuarioAutorizador = usuarioDAO.buscarPorCodigo(codigoAutorizador);
		Usuario usuarioAutorizar = usuarioDAO.buscarPorCodigo(codigoUsuario);
		
		if (usuarioAutorizador.getCodUnidade() == usuarioAutorizar.getCodUnidade()) {
			usuarioAutorizar.setStatus(status);
			
			log.debug("Iniciando persistência do usuário");
			usuarioDAO.atualizar(usuarioAutorizar);
			
			enviarEmailUsuario(status, usuarioAutorizar);
			
		} else {
			throw new FrotasException(msg.autorizacaoInvalida(usuarioAutorizar.getLogin()));
		}
	}
	
	/**
	 * @param aguardandoAprovacao
	 * @param usuario
	 */
	private void enviarEmailUsuario(int aguardandoAprovacao, Usuario usuario) {
		log.trace("Efetuando envio de e-mail para o usuário " + usuario.getEmail());
		
		switch (aguardandoAprovacao) {
		case UsuarioConstants.AGUARDANDO_APROVACAO:
			MailUtil.sentEmail("Cadastro de Usuário", "Seu usuário foi cadastrado com sucesso e foi encaminhado para aprovação.", usuario.getEmail());
			break;

		case UsuarioConstants.ATIVO:
			MailUtil.sentEmail("Cadastro de Usuário", "Seu usuário foi autorizado com sucesso e você já pode utilizar o Fiesc Frotas.", usuario.getEmail());
			break;
			
		case UsuarioConstants.INATIVO:
			MailUtil.sentEmail("Cadastro de Usuário", "Seu usuário não foi autorizado. Verifique com o autorizador da sua unidade o motivo.", usuario.getEmail());
			break;
			
		default:
			break;
		}
	}

}