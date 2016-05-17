package br.com.fiescfrotas.dao;

import java.util.List;

import br.com.fiescfrotas.constants.UsuarioConstants;
import br.com.fiescfrotas.modelo.Perfil;
import br.com.fiescfrotas.modelo.Usuario;

public class UsuarioDAO extends GenericDAO<Long, Usuario> {

	public UsuarioDAO() {
		super();
	}
	
	/**
	 * Método para verificar se o usuario existe.
	 * 
	 * @param usuario
	 *            Usuario - Objeto usuario
	 * @return Objeto usuario
	 */
	public Usuario buscarLogin(Usuario usuario) {
		String sql = "SELECT u FROM "+ Usuario.class.getSimpleName()
				+ " u WHERE u.login = :login and "
				+ "u.senha = :senha";

		usuario = this.simpleEntityManager.getEntityManager()
				.createQuery(sql, Usuario.class)
				.setParameter("login", usuario.getLogin())
				.setParameter("senha", usuario.getSenha())
				.getSingleResult();

		return usuario;
	}
	
	/**
	 * Método para buscar todos os usuários ativos.
	 * 
	 *            List<Usuario> - Objeto usuario
	 * @return List<Usuario> usuario
	 */
	public List<Usuario> buscarTodosUsuariosAtivos(){
		String sql = "SELECT u FROM " + Usuario.class.getSimpleName() + " u WHERE u.status <> :status";
		return this.simpleEntityManager.getEntityManager().createQuery(sql, Usuario.class).setParameter("status", UsuarioConstants.INATIVO).getResultList();
	}
		
	/**
	 * Método para buscar todos os usuários sem aprovação.
	 * 
	 *            List<Usuario> - Objeto usuario
	 * @return List<Usuario> usuario
	 */
	public List<Usuario> buscarTodosUsuariosParaAprovacao(long codUnidade){
		String sql = "SELECT u FROM " + Usuario.class.getSimpleName() + " u WHERE u.status = :status and u.codUnidade = :codUnidade";
		return this.simpleEntityManager.getEntityManager()
									   .createQuery(sql, Usuario.class)
									   .setParameter("status", UsuarioConstants.AGUARDANDO_APROVACAO)
									   .setParameter("codUnidade", codUnidade)
									   .getResultList();
	}

	/**
	 * Método para buscar todos os usuários ativos.
	 * 
	 * @param cnh
	 * @param codigo 
	 * @param email
	 * @param login
	 * @param matricula
	 * 
	 *            List<Usuario> - Objeto usuario
	 * @return List<Usuario> usuario
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodosUnicos(long codigo, long cnh, String email, String login, int matricula) {
		
		String sql = "SELECT u FROM " + Usuario.class.getSimpleName()
				+ " u WHERE (u.cnh = :cnh OR u.email = :email OR u.login = :login OR u.matricula = :matricula) and u.codigo <> :codigo";
		
		return this.simpleEntityManager.getEntityManager().createQuery(sql).setParameter("cnh", cnh)
				.setParameter("email", email).setParameter("login", login).setParameter("matricula", matricula)
				.setParameter("codigo", codigo).getResultList();
	}
	
	public Boolean buscarAutorizacaoPerfil(long perfil, String uri){
		String sql = "SELECT count(u) FROM " + Perfil.class.getSimpleName()+ " u JOIN u.paginas p WHERE u.codigo = :perfil AND p.uri = :uri"; 
		return ((Long) this.simpleEntityManager
							  .getEntityManager()
							  .createQuery(sql)
							  .setParameter("perfil", perfil)
							  .setParameter("uri", uri)
							  .getSingleResult()) > 0;
	}

}