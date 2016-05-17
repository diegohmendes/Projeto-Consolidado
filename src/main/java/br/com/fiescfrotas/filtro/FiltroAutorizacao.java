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

import br.com.fiescfrotas.modelo.Usuario;
import br.com.fiescfrotas.servicos.UsuarioServicos;

/**
 * Servlet Filter implementation class FiltroAutenticacao
 */
public class FiltroAutorizacao implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		HttpServletRequest req = (HttpServletRequest) request;
		String context = req.getContextPath();

		String uri = req.getRequestURI().substring(context.length());
		long perfil = (long) session.getAttribute("perfil");	
		long id = (long) session.getAttribute("id");
		
		try{
			Usuario usuario = new Usuario();
			UsuarioServicos usuarioServicos = new UsuarioServicos();
			usuario = usuarioServicos.buscarPorCodigo(id);
			if(usuario.getPerfil().getTipoPerfil() == perfil){
				boolean rs = usuarioServicos.buscarAutorizacaoPagina(perfil, uri);
				if(rs) {
					chain.doFilter(request, response);
				} else {
					((HttpServletResponse) response).sendRedirect(context +"/login.html");
				}
			} else {
				((HttpServletResponse) response).sendRedirect(context +"/login.html");
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
