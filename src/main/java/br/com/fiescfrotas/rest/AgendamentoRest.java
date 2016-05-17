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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import br.com.fiescfrotas.constants.AgendamentoConstants;
import br.com.fiescfrotas.modelo.Agendamento;
import br.com.fiescfrotas.servicos.AgendamentoServicos;
import br.com.fiescfrotas.util.RestUtil;

@Path("agendamento")
@Api(value = "Agendamento", description = "Operações com Agendamento")
public class AgendamentoRest extends RestUtil {

	AgendamentoServicos agendamentoService = new AgendamentoServicos();
	
	@Context
	private HttpServletRequest request;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/inserir")
	@ApiOperation(value = "Inserir Agendamento", notes = "Operações de inserção de agendamentos", response = Agendamento.class)

	public Response inserirAgendamento(String param) {
		try {
			Agendamento agendamento = new ObjectMapper().readValue(param, Agendamento.class);
			agendamentoService.inserir(agendamento);

			return this.buildResponse("Agendamento cadastrado.");
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("buscar")
	@ApiOperation(value = "Buscar Todos os Agendamentos", notes = "Operação para retornar todos os agendamentos cadastrados por usuário.", response = Agendamento.class)
	public Response buscarTodos() {
		try {
			List<Agendamento> agendamento = agendamentoService.buscarTodos();
			return this.buildListResponse(agendamento);
		} catch (Exception e) {
			return this.buildErrorResponse("Nenhum agendamento cadastrado para ~Usuario~");
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("buscarUsuario")
	@ApiOperation(value = "Buscar Todos os Agendamentos", notes = "Operação para retornar todos os agendamentos cadastrados por usuário.", response = Agendamento.class)
	public Response buscarAgendamentosPorUsuario(@QueryParam("codUsuario") long codUsuario) {
		try {
			List<Agendamento> agendamento = agendamentoService.buscarAgendamentosPorUsuario(codUsuario);
			return this.buildListResponse(agendamento);
		} catch (Exception e) {
			return this.buildErrorResponse("Nenhum agendamento cadastrado para ~Usuario~");
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarVeiculo/{codVeiculo}")
	@ApiOperation(value = "Buscar Agendamentos por codVeiculo", notes = "Operação para retornar todos os agendamentos por codVeiculo.", response = Agendamento.class)
	public Response buscarAgendamentoPorVeiculo(@PathParam("codVeiculo") long codVeiculo) {
		try {
			List<Agendamento> agendamento = agendamentoService.buscarAgendamentoPorVeiculo(codVeiculo);

			if (agendamento.isEmpty()) {
				return this.buildErrorResponse("Sem agendamento para este carro.");
			} else
				return this.buildListResponse(agendamento);

		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/buscarAgendamento/{codAgendamento}")
	@ApiOperation(value = "Buscar Agendamento por codAgendamento", notes = "Operação para buscar agendamento por codAgendamento.", response = Agendamento.class)
	public Response buscarPorCodAgendamento(@PathParam("codAgendamento") long codAgendamento) {
		try {
			Agendamento agendamento = agendamentoService.buscarPorCodigo(codAgendamento);

			return this.buildListResponse(agendamento);

		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}

	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cancelar/{codAgendamento}")
	@ApiOperation(value = "Cancelar Agendamento", notes = "Operação para inativar um agendamento.")
	public Response cancelarAgendamento(@PathParam("codAgendamento") long codAgendamento) {
		try {
			agendamentoService.cancelar(codAgendamento);
			return this.buildResponse("Agendamento Cancelado com sucesso.");
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}

	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/atualizar")
	@ApiOperation(value = "Atualizar Agendamento", notes = "Operação para atualizar os dados de um agendamento.")
	public Response atualizarAgendamento(String param) {
		try {
			Agendamento agendamento = new ObjectMapper().readValue(param, Agendamento.class);
			agendamentoService.atualizar(agendamento);
			return this.buildResponse("Agendamento Atualizado com sucesso.");
		} catch (Exception e) {
			return this.buildErrorResponse("Agendamento Cancelado.");
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("aprovacao")
	@ApiOperation(value = "Atualizar Agendamento", notes = "Operação para atualizar os dados de um agendamento.")
	public Response buscarAgendamentosParaAprovacao() {
		try {
			List<Agendamento> agendamento = agendamentoService.buscarAgendamentosParaAprovacao((long) request.getSession(true).getAttribute("id"));
			return this.buildListResponse(agendamento);
		} catch (Exception e) {
			return this.buildErrorResponse("Nenhum agendamento cadastrado para ~Usuario~");
		}
	}

	@POST
	@Path("aprovar/{codAgendamento}/{validacao}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Atualizar Agendamento", notes = "Operação para atualizar os dados de um agendamento.")
	public Response buscarAgendamentosParaAprovacao(@PathParam("codAgendamento") String codigo,
													@PathParam("validacao") String validacao) {
		try {
			
			if(validacao.equals("1")) 
				this.agendamentoService.alterarStatus(AgendamentoConstants.ATIVO, 
													  Long.parseLong(codigo), 
													  (long) request.getSession(true).getAttribute("id"));
			else
				this.agendamentoService.alterarStatus(AgendamentoConstants.INATIVO, 
													  Long.parseLong(codigo), 
													  (long) request.getSession(true).getAttribute("id"));
			
			return this.buscarAgendamentosParaAprovacao();
		} catch (Exception e) {
			return this.buildErrorResponse("Nenhum agendamento cadastrado para ~Usuario~");
		}
	}

}