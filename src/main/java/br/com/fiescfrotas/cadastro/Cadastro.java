package br.com.fiescfrotas.cadastro;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import br.com.fiescfrotas.modelo.Usuario;
import br.com.fiescfrotas.servicos.UsuarioServicos;
import br.com.fiescfrotas.util.MensagemUtil;
import br.com.fiescfrotas.util.RestUtil;

@Path("/cadastro")
@Api(value = "Usuario", description = "Operações com usuário")
@Consumes("application/*")

public class Cadastro extends RestUtil{
	
	private Logger log = Logger.getLogger(Cadastro.class);
	private UsuarioServicos usuarioService = new UsuarioServicos();
	private MensagemUtil msg = new MensagemUtil();
	
	@POST
	@Path("/inserir")
	@Produces(MediaType.TEXT_PLAIN)
	@ApiOperation(value = "Inserir Usuário", 
				  notes = "Operação de Inserção de Usuário", 
				  response = Usuario.class)
	public Response inserir(@ApiParam(value = "Objeto: Usuario", required = true) String userParam) {
		log.info("Iniciando comunicação com sucesso em inserção de usuário");
		System.out.println(userParam);

		try {
			Usuario usuario = new ObjectMapper().readValue(userParam, Usuario.class);
			
			usuarioService.inserir(usuario);
			
			log.info("Finalizando comunicação com sucesso em inserção de usuário");
			
			return this.buildResponse(msg.cadastrado(usuario));
		} catch (Exception e) {
			
			log.info("Finalizando comunicação com erro em inserção de usuário");
			log.error("Erro ao inserir usuário: " + e.getMessage());
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
}