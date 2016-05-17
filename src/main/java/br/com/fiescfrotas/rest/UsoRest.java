package br.com.fiescfrotas.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import br.com.fiescfrotas.modelo.Uso;
import br.com.fiescfrotas.servicos.UsoServicos;
import br.com.fiescfrotas.util.RestUtil;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

@Path("/uso")
@Api(value = "Uso", description = "Operação com Uso")
public class UsoRest extends RestUtil{
	
	UsoServicos usoService = new UsoServicos();
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/inserir")
	@ApiOperation(value = "Inserir Uso", 
				  notes = "Operações de inserção de uso", 
				  response = Uso.class)
	public Response inserirUso(String param) throws JsonParseException, JsonMappingException, IOException{
		try{
			Uso uso = new ObjectMapper().readValue(param, Uso.class);
			usoService.inserir(uso);
			return this.buildResponse("Uso inserido com sucesso.");
		}catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Não foi possível inserir o uso.");
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar")
	@ApiOperation(value = "buscar uso", 
				  notes = "Operação para retornar todos os usos.",
				  response = Uso.class)
	public Response buscarTodos() throws JsonParseException, JsonMappingException, IOException {
		try{
			List<Uso> usoList = usoService.buscarTodos();
			return this.buildListResponse(usoList);
		} catch(Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse("Não foi possível efetuar a busca dos usos.");
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar/{codigo}")
	@ApiOperation(value = "buscar por codigo", 
				  notes = "Operação para buscar uso por codigo.", 
				  response = Uso.class)
	public Response buscarPorCodigo(@PathParam("codigo") long codigo) {
		try{
			Uso uso = usoService.buscarPorCodigo(codigo);
			return this.buildListResponse(uso);
		}catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse("Não foi possível efetuar a busca o uso específico.");
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cancelar/{id}")
	@ApiOperation(value = "Cancelar Uso", 
				  notes = "Operação para inativar um agendamento.")
	public Response cancelar(@PathParam("codigo") long codigo) {
		try{
			usoService.cancelar(codigo);
			return this.buildResponse("Uso Cancelado com sucesso.");
		} catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse("Não foi possível cancelar o uso específico.");
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/atualizar")
	@ApiOperation(value="Atualizar Uso", 
				  notes="Operação para atualizar o uso.")
	public Response atualizarAgendamento(String param) throws JsonParseException, JsonMappingException, IOException {
		try{
			Uso uso = new ObjectMapper().readValue(param, Uso.class);
			usoService.atualizar(uso);
			return this.buildResponse("Uso atualizado com sucesso.");
		}catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse("Não foi possível atualizar este uso.");
		}
	}
}