package br.com.fiescfrotas.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FiltroConexao implements Filter {

	public void destroy() {

	}

	/**
	 * Cria o Filter
	 * 
	 * @param FilterConfig
	 *            fConfig
	 * @return void
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

	/**
	 * Verifica se o usuário é válido e libera o acesso a área restrita
	 * 
	 * @param ServletRequest
	 *            request, ServletResponse response, FilterChain chain
	 * @return void
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		/*
		 * O método getContextPath é responsavel por retornar o contexto da URL
		 * que realizou a requisição
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		String context = req.getContextPath();

		try {
			/*
			 * O método getSession é responsavel por pegar a sessão ativa. Aqui
			 * foi necessário fazer um casting pois o objetivo request é do tipo
			 * ServletRequest e não HttpServletRequest como no servlet onde você
			 * utiliza o método em questão sem o uso do casting
			 */
			HttpSession session = ((HttpServletRequest) request).getSession();
			String usuario = null;
			if (session != null) {
				usuario = (String) session.getAttribute("login");
			}
			if (usuario == null) {
				/*
				 * Aqui esta sendo setado um atributo na sessão para que depois
				 * possamos exibir uma mensagem ao usuário.
				 */
				session.setAttribute("msg", "Você não está logado no sistema!");
				/*
				 * Utilizamos o método sendRedirect que altera a URL do
				 * navegador para posicionar o usuário na tela de login, que
				 * neste caso página index.html Note que não precisamos utilizar
				 * o recurso "../../" para informar o caminho de página
				 * index.html, a variavel do contexto ja posicione no inicio da
				 * URL.
				 */
				((HttpServletResponse) response).sendRedirect(context
						+ "/login.html");
			} else {
				/*
				 * Caso exista um usuário valido (diferente de nulo) envia a
				 * requisição para a página que se deseja acessar, ou seja,
				 * permite o acesso, deixa passar.
				 */
				chain.doFilter(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}