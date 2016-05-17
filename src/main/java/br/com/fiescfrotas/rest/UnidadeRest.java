package br.com.fiescfrotas.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import br.com.fiescfrotas.modelo.Unidade;
import br.com.fiescfrotas.servicos.UnidadeServicos;
import br.com.fiescfrotas.util.RestUtil;

@Path("unidade")
@Api(value="Unidade", description="Operações de busca de Unidade")
public class UnidadeRest extends RestUtil{

	
	UnidadeServicos unidadeServico = new UnidadeServicos();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("buscar")
	@ApiOperation(value="Buscar Todas as Unidades", 
				  notes="Operação para retornar todas as unidades cadastradas no sistema.", 
				  response=Unidade.class)
	public Response buscarTodos() {
		try {
			List<Unidade> unidade = unidadeServico.buscarTodos();
			return this.buildListResponse(unidade);
		} catch (Exception e) {
			return this.buildErrorResponse("Nenhum agendamento cadastrado para ~Usuario~");
		} 
	}
}
