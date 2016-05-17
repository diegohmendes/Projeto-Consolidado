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

import org.apache.log4j.Logger;

import br.com.fiescfrotas.modelo.Veiculo;
import br.com.fiescfrotas.servicos.VeiculoServicos;
import br.com.fiescfrotas.util.MensagemUtil;
import br.com.fiescfrotas.util.RestUtil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/veiculo")
@Api(value = "Veículo", description = "Operações com veículo")
@Consumes("application/*")
public class VeiculoRest extends RestUtil {

	static Logger log = Logger.getLogger(VeiculoRest.class);
	private VeiculoServicos veiculoService = new VeiculoServicos();
	private MensagemUtil msg = new MensagemUtil();

	@POST
	@Path("/inserir")
	@ApiOperation(value = "Inserir Veiculo", notes = "Operação de inserção de veículo", response = Veiculo.class)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(@ApiParam(value = "Objeto : Veiculo'", required = true) String param) {
		
		log.info("Iniciando comunicação com sucesso em inserir veículo");
		
		try {
			Veiculo veiculo = new ObjectMapper().readValue(param, Veiculo.class);
			veiculoService.inserir(veiculo);

			log.info("Finalizando comunicação com sucesso em inserção de veículo");
			
			return this.buildResponse(msg.cadastrado(veiculo));
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em inserção de veículo");
			log.error("Erro ao inserir veículo: " + e.getMessage());
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/buscar")
	@ApiOperation(value = "Buscar veículos", notes = "Operação de busca de veículo", response = Veiculo.class, responseContainer = "List")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarTodos() {
		
		log.info("Iniciando comunicação com sucesso em busca de veículos");
		
		try {
			List<Veiculo> veiculo = veiculoService.buscarTodos();
			
			log.info("Finalizando comunicação com sucesso em busca de veículos");
			
			return this.buildListResponse(veiculo);
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em busca de veículos");
			log.error("Erro ao buscar veículos: " + e.getMessage());
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscar/{codVeiculo}")
	@ApiOperation(value = "Buscar veículos", notes = "Operação de busca veículo", response = Veiculo.class, responseContainer = "String")
	public Response buscarPorId(@PathParam("codVeiculo") long codVeiculo) {
		
		log.info("Iniciando comunicação com sucesso em busca de veículo");
		
		try {
			Veiculo veiculo = veiculoService.buscarPorCodigo(codVeiculo);
			
			log.info("Finalizando comunicação com sucesso em busca de veículo");
			
			return this.buildListResponse(veiculo);
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em busca de veículo disponíveis");
			log.error("Erro ao buscar veículos disponíveis: " + e.getMessage());
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cancelar/{codVeiculo}")
	@ApiOperation(value = "Cancelar veículo", notes = "Operação de cancelar veículo", response = Veiculo.class, responseContainer = "String")
	public Response cancelarVeiculo(@PathParam("codVeiculo") long codVeiculo) {
		
		log.info("Iniciando comunicação com sucesso em cancelar de veículo");
		
		try {
			return this.buildResponse(veiculoService.cancelar(codVeiculo));
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em cancelar de veículo");
			log.error("Erro ao cancelar veículos: " + e.getMessage());
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/atualizar")
	@ApiOperation(value = "Atualizar veículo", notes = "Operação de atualizar veículo.")
	public Response atualizarVeiculo(String param) {
		
		log.info("Iniciando comunicação com sucesso em atualizar veículo");
		
		try {
			Veiculo veiculo = new ObjectMapper().readValue(param, Veiculo.class);
			veiculoService.atualizar(veiculo);
			
			log.info("Finalizando comunicação com sucesso em atualizar veículo");

			return this.buildResponse("Veículo atualizado com sucesso");
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em atualizar veículo");
			log.error("Erro ao atualizar veículo: " + e.getMessage());
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/disponiveis")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Atualizar veículo", notes = "Operação de atualizar veículo.")
	public Response buscarCarrosDisponiveis() {

		log.info("Iniciando comunicação com sucesso em busca de veículo disponíveis");
		
		try {
			List<Veiculo> veiculo = veiculoService.buscarVeiculosDisponiveis();
			log.info("Finalizando comunicação com sucesso em busca de veículo disponíveis");
		
			return this.buildListResponse(veiculo);
		} catch (Exception e) {
			log.info("Finalizando comunicação com erro em busca de veículo disponíveis");
			log.error("Erro ao buscar veículos disponíveis: " + e.getMessage());
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
}