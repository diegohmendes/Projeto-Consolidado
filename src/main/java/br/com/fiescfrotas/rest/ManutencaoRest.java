package br.com.fiescfrotas.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.fiescfrotas.modelo.Manutencao;
import br.com.fiescfrotas.servicos.ManutencaoServicos;
import br.com.fiescfrotas.util.RestUtil;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/manutencao")
@Api(value = "Manutencao", description = "Operações com Manutenção")
@Consumes("application/*")
public class ManutencaoRest extends RestUtil {
	
	private ManutencaoServicos manutencaoService = new ManutencaoServicos();

	@POST
	@Path("/inserir")
	@ApiOperation(value = "Inserir manutenção", 
				  notes = "Operação de inserção de manutenção", 
				  response = Manutencao.class)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(String manutParam) {
		try {
			Manutencao manutencao = new ObjectMapper().readValue(manutParam, Manutencao.class);
			manutencaoService.inserir(manutencao);
			return this.buildResponse("Manutenção inserida com sucesso");
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/buscar")
	@ApiOperation(value = "Buscar manutenções", notes = "Operação de busca de manutenções", response = Manutencao.class, responseContainer = "List")
	@Produces(MediaType.TEXT_PLAIN)
	public Response buscarManutencao() {
		try {
			List<Manutencao> listaManutencao = manutencaoService.buscarTodos();
			return this.buildListResponse(listaManutencao);
		} catch (Exception e) {
			return this.buildResponse("Nenhuma manutenção cadastrado para a ~Manutenção~");
		}
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/buscar/{codigo}")
	@ApiOperation(value = "Buscar manutenção", 
				  notes = "Operação de busca de manutenção ", 
				  response = Manutencao.class, 
				  responseContainer = "List")
	public Response buscarManutencaoPorId(@ApiParam(value = "codigo", 
													allowableValues = "range[1,"+ Integer.MAX_VALUE + "]", 
													required = true) 
										  @PathParam("codigo") long codigo) {
		try {
			Manutencao manutencao = (Manutencao) manutencaoService.buscarPorCodigo(codigo);
			return this.buildResponse(manutencao);
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/cancelar/{codigo}")
	@ApiOperation(value = "Cancelar manutenção", 
				  notes = "Operação de cancelamento de manutenção")
	public Response cancelarManutencao(@ApiParam(value = "codigo", 
												 allowableValues = "range[1,"+ Integer.MAX_VALUE + "]", 
												 required = true) 
									   @PathParam("codigo") long codigo) {
		try {
			manutencaoService.remover(codigo);
			return this.buildResponse("Manutenção cancelada com sucesso");
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/atualizar")
	@ApiOperation(value = "Atualizar manutenção", notes = "Operação de atualizar manutenção", response = Manutencao.class)
	public Response atualizarManutencao(@ApiParam(value = "{Objeto: Manutenção", required = true) String manutParam) {
		try {
			Manutencao manutencao = new ObjectMapper().readValue(manutParam, Manutencao.class);
			manutencaoService.atualizar(manutencao);
			return this.buildResponse("Manutenção atualizada com sucesso");
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}

}