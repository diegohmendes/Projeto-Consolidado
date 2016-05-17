package br.com.fiescfrotas.rest;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiescfrotas.modelo.Perfil;
import br.com.fiescfrotas.util.RestUtil;

@Path("sessao")
public class SessaoRest extends RestUtil {
	private HttpSession sessao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar/{login}")
	public Response buscarDadosSessao(@PathParam("login") String login){
		
		try{
			Perfil perfil = new Perfil();
			sessao = null;
			
			perfil = (Perfil) sessao.getAttribute("Perfil");
			return this.buildResponse(perfil);
		} catch(Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e);
		}
	}
	
	
	
	
	
	
	
}
