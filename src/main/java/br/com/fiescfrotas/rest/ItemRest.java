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
import org.codehaus.jackson.map.ObjectMapper;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import br.com.fiescfrotas.modelo.Item;
import br.com.fiescfrotas.servicos.ItemServicos;
import br.com.fiescfrotas.util.RestUtil;

@Path("/item")
@Api(value = "Item", description = "Operações com Item")
@Consumes("application/*")
public class ItemRest extends RestUtil {

	static Logger log = Logger.getLogger(ItemRest.class);
	private ItemServicos itemService = new ItemServicos();

	@POST
	@Path("/inserir")
	@ApiOperation(value = "Inserir item", notes = "Operação de inserção de item na manutenção", response = Item.class, responseContainer = "String")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarItem(String itemParam) {
		try {
			Item item = new ObjectMapper().readValue(itemParam, Item.class);
			itemService.inserir(item);
			return this.buildResponse("Item inserido na manutenção " + item.getCodManutencao() + " com sucesso");
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/buscar/{codigo}")
	@ApiOperation(value = "Buscar item", 
				  notes = "Operação de busca de item na manutenção", 
				  response = Item.class, 
				  responseContainer = "List")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarItem(@ApiParam(value = "codigo", 
										 allowableValues = "range[1," + Integer.MAX_VALUE+ "]", 
										 required = true) 
							   @PathParam("codigo") long codigo) {
		try {
			List<Item> listaItem = itemService.buscarTodos(codigo);
			return this.buildListResponse(listaItem);
		} catch (Exception e) {
			return this.buildErrorResponse("Nenhum item cadastrado para a ~Manutenção~");
		}
	}

	@DELETE
	@Path("/cancelar/{codigo}")
	@ApiOperation(value = "Cancelar item", 
				  notes = "Operação de cancelamento de item na manutenção", 
				  responseContainer = "String")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cancelarItem(@ApiParam(value = "codigo", 
										   allowableValues = "range[1," + Integer.MAX_VALUE+ "]", 
										   required = true) 
								 @PathParam("codigo") long codigo) {
		try {
			itemService.cancelar(codigo);
			return this.buildResponse("Item cancelado com sucesso na manutenção " + codigo);
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@PUT
	@Path("/atualizar")
	@ApiOperation(value = "Atualiza item", 
				  notes = "Operação de atualização de item na manutenção", 
				  response = Item.class, 
				  responseContainer = "String")
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarItem(@ApiParam(value = "Objeto: Item", 
											required = true) 
											String itemParam) {
		try {
			Item item = new ObjectMapper().readValue(itemParam, Item.class);
			itemService.atualizar(item);
			return this.buildResponse("Item atualizado na manutenção "+item.getCodManutencao()+" com sucesso");
		} catch (Exception e) {
			return this.buildErrorResponse(e.getMessage());
		}
	}
}