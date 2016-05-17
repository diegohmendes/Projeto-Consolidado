package br.com.fiescfrotas.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import br.com.fiescfrotas.constants.AgendamentoConstants;
import br.com.fiescfrotas.constants.UsuarioConstants;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.modelo.Usuario;
import br.com.fiescfrotas.servicos.UsuarioServicos;
import br.com.fiescfrotas.util.RestUtil;

@Path("/usuario")
@Api(value = "Usuario", description = "Operações com usuário")
@Consumes("application/*")
public class UsuarioRest extends RestUtil {

	static Logger log = Logger.getLogger(UsuarioRest.class);
	private UsuarioServicos usuarioService = new UsuarioServicos();
	@Context
	private HttpServletRequest request;

	@GET
	@Path("/buscar")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Buscar Usuários", notes = "Operação de Busca de todos os Usuários", response = Usuario.class, responseContainer = "List")
	public Response buscarUsuario() {

		log.info("Iniciando comunicação com sucesso em buscar usuários");

		try {
			List<Usuario> listaUsuario = usuarioService.buscarTodos();

			log.info("Finalizando comunicação com sucesso em buscar usuários");

			return this.buildListResponse(listaUsuario);
		} catch (Exception e) {

			log.info("Finalizando comunicação com erro em buscar usuários");
			log.error("Erro ao buscar usuários: " + e.getMessage());
			return this.buildErrorResponse("Nenhum usuário cadastrado");
		}
	}
	

	@GET
	@Path("/buscar/{codigo}")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Buscar Usuário", notes = "Operação de Busca de Usuário", response = Usuario.class, responseContainer = "String")
	public Response buscarUsuarioPorId(@ApiParam(value = "codigo", allowableValues = "range[1," + Long.MAX_VALUE
			+ "]", required = true) @PathParam("codigo") long codigo) {
		log.info("Iniciando comunicação com sucesso em buscar usuário");

		try {
			Usuario usuario = usuarioService.buscarPorCodigo(codigo);

			log.info("Finalizando comunicação com sucesso em buscar usuário");

			return this.buildResponse(usuario);
		} catch (Exception e) {

			log.info("Finalizando comunicação com erro em buscar usuário");
			log.error("Erro ao buscar usuário: " + e.getMessage());

			return this.buildErrorResponse(e.getMessage());
		}
	}

	@DELETE
	@Path("/cancelar/{codigo}")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Cancelar usuário", notes = "Operação de desabilitar de usuário", response = Usuario.class, responseContainer = "String")
	public Response cancelar(@ApiParam(value = "codigo", allowableValues = "range[1," + Long.MAX_VALUE
			+ "]", required = true) @PathParam("codigo") long codigo) {

		log.info("Iniciando comunicação com sucesso em cancelar usuário");

		try {

			usuarioService.atualizar(codigo);

			log.info("Finalizando comunicação com sucesso em cancelar usuário");

			return this.buildResponse("Usuário desabilitado");
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em cancelar usuário");
			log.error("Erro ao cancelar usuário: " + e.getMessage());

			return this.buildErrorResponse(e.getMessage());
		}
	}

	@PUT
	@Path("/atualizar")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Atualizar usuário", notes = "Operação de atualização de usuário", response = Usuario.class, responseContainer = "String")
	public Response atualizarUsuario(@ApiParam(value = "'Objeto': Usuario", required = true) String userParam) {

		log.info("Iniciando comunicação com sucesso em atualizar usuário");

		try {
			Usuario usuario = new ObjectMapper().readValue(userParam, Usuario.class);
			usuarioService.atualizar(usuario);

			log.info("Finalizando comunicação com sucesso em atualizar usuário");
			return this.buildResponse("Usuário atualizado com sucesso");
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em atualizar usuário");
			log.error("Erro ao atualizar usuário: " + e.getMessage());

			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/aprovacao")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Buscar Usuarios Para Aprovação", notes = "Operação de buscar usuarios Para aprovação", response = Usuario.class, responseContainer = "String")
	public Response buscarUsuarioParaAprovacao() {
		
		log.info("Iniciando comunicação com sucesso em buscar usuários para aprovação");

		try {
			List<Usuario> listaUsuario = usuarioService.buscarUsuariosSemAprovacao((long) request.getSession(true).getAttribute("id"));

			log.info("Finalizando comunicação com sucesso em buscar usuários para aprovação");
			
			return this.buildListResponse(listaUsuario);
		} catch (Exception e) {

			log.info("Finalizando comunicação com erro em buscar usuários para aprovação");
			log.error("Erro ao buscar usuários: " + e.getMessage());
			return this.buildErrorResponse("Nenhum usuário para aprovação");
		}
	}
	
	/**
	 * Método usado para buscar os usuários que não foram aprovados ainda.
	 * 
	 * @param codigo 
	 * 			Código do usuário que será aprovado.
	 * @param validacao
	 * 			 Validação que vem da tela que diferencia se ele vai ser aprovado ou não. Valores: 1 - Aprovado; 0 - Reprovado;
	 * @return Lista de Usuario
	 * @throws FrotasException
	 * */
	
	@POST
	@Path("/aprovar/{codUsuario}/{validacao}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Desautorizar usuário", 
			      notes = "Operação de desautorização de usuário", 
				  response = Usuario.class, responseContainer = "String")
	public Response aprovarUsuario(@ApiParam(value = "codigo", allowableValues = "range[1," + Long.MAX_VALUE + "]", required = true) 
									@PathParam("codUsuario") String codigo, 
									@PathParam("validacao") String validacao) {

		log.info("Iniciando comunicação com sucesso em desautorizar usuário");
		
		try {
			if(validacao.equals("1")) 
				this.usuarioService.alterarStatus(UsuarioConstants.ATIVO, Long.parseLong(codigo), (long) request.getSession(true).getAttribute("id"));
			 else 
				this.usuarioService.alterarStatus(UsuarioConstants.INATIVO, Long.parseLong(codigo), (long) request.getSession(true).getAttribute("id"));
				
			log.info("Finalizando comunicação com sucesso em desautorização de usuário");
			return this.buscarUsuarioParaAprovacao();
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em desautorizar usuário");
			log.error("Erro ao autorizar usuário: " + e.getMessage());
			
			return this.buildErrorResponse(e.getMessage());
		}
	}

}