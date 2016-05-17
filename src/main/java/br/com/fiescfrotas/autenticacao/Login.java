package br.com.fiescfrotas.autenticacao;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fiescfrotas.dao.UsuarioDAO;
import br.com.fiescfrotas.modelo.Usuario;
import br.com.fiescfrotas.util.ConversionUtil;

public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ConversionUtil conversionUtil = new ConversionUtil();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	/**
	 * @see HttpServlet#HttpServlet
	 */
	public Login() {
		super();
	}

	/**
	 * @param HttpServletRequest
	 *            request, HttpServletResponse response
	 * @return void
	 */
	protected void process(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		byte[] valueDecoded = Base64.getDecoder().decode(request.getParameter("senha").getBytes());
		String password = new String(valueDecoded);
		String hash = conversionUtil.md5(password);

		Usuario usuario = new Usuario();
		usuario.setLogin(request.getParameter("login"));
		usuario.setSenha(hash);

		HttpSession sessao = null;
		//String contexto = request.getContextPath();
		
		try {
			usuario = usuarioDAO.buscarLogin(usuario); 
			sessao = request.getSession(true);
			sessao.setAttribute("login", usuario.getLogin());
 			sessao.setAttribute("id", usuario.getCodigo());
 			sessao.setAttribute("perfil", usuario.getPerfil().getTipoPerfil());
 			
 			response.getWriter().write("1");
			
		} catch (Exception e) {
			request.setAttribute("erro", "Não foi possível fazer a conexão");
			request.getRequestDispatcher("/login.html").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
}