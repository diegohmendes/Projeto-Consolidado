package br.com.fiescfrotas.servicos;

import java.util.List;

import org.apache.log4j.Logger;

import br.com.fiescfrotas.constants.AgendamentoConstants;
import br.com.fiescfrotas.dao.AgendamentoDAO;
import br.com.fiescfrotas.exception.FrotasException;
import br.com.fiescfrotas.interfaces.IServicos;
import br.com.fiescfrotas.modelo.Agendamento;
import br.com.fiescfrotas.modelo.Usuario;
import br.com.fiescfrotas.util.MensagemUtil;

public class AgendamentoServicos implements IServicos<Agendamento> {
	static Logger log = Logger.getLogger(AgendamentoServicos.class);

	private AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
	private MensagemUtil msg = new MensagemUtil();

	@Override
	/**
	 * Método para inserir agendamento.
	 * 
	 * @param agendamento
	 *            Agendamento - Objeto agendamento
	 * @return void
	 */
	public void inserir(Agendamento agendamento) {
		agendamentoDAO.inserir(agendamento);
	}

	/**
	 * Método para buscar todos os agendamentos - Sem filtro.
	 * 
	 * 
	 * @return List<Agendamento> - Uma lista de agendamento encontrado no banco.
	 */
	public List<Agendamento> buscarTodos() throws FrotasException{
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		List<Agendamento> listaDeAgendamento = agendamentoDAO.buscarPorAgendamentosAtivos();
		if(listaDeAgendamento.isEmpty())
			throw new FrotasException("Não existem agendamentos para este usuario");
		else{
			return listaDeAgendamento;
		}
	}

	/**
	 * Método para buscar um agendamento específico.
	 * 
	 * @param agendamento
	 *            Agendamento - Objeto agendamento
	 * @return Objeto agendamento
	 * @throws FrotasException
	 */
	public Agendamento buscarPorCodigo(long codigo) throws FrotasException {
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		Agendamento agendamento = null;
		agendamento = agendamentoDAO.buscarPorCodigo(codigo);

		if (agendamento == null)
			throw new FrotasException("Agendamento não existente.");
		else if (!(agendamento.getStatus() == AgendamentoConstants.INATIVO))
			throw new FrotasException("Agendamento cancelado.");
		else
			return agendamento;
	}

	/**
	 * @param codigo
	 */
	public void cancelar(long codigo) {
		Agendamento agendamento = (Agendamento) agendamentoDAO.buscarPorCodigo(codigo);
		agendamento.setStatus(AgendamentoConstants.INATIVO);
		agendamentoDAO.atualizar(agendamento);
	}

	public void atualizar(Agendamento agendamento) throws FrotasException {
		if(agendamento.getStatus() == AgendamentoConstants.INATIVO) {
			throw new FrotasException("Agendamento cancelado.");
		} else {
			agendamentoDAO.atualizar(agendamento);
		}
	}

	/**
	 * @param codVeiculo
	 * @return
	 * @throws FrotasException
	 */
	public List<Agendamento> buscarAgendamentoPorVeiculo(long codVeiculo) throws FrotasException {
		List<Agendamento> listaDeAgendamento = agendamentoDAO.buscarAgendamentoPorVeiculo(codVeiculo);
		if(listaDeAgendamento.isEmpty())
			throw new FrotasException("Não existem agendamentos para este usuario");
		else{
			return listaDeAgendamento;
		}
	}
	
	/**
	 * @param codigo
	 * @return
	 */
	public boolean buscarAgendamentoPorVeiculo2(long codigo) {
		return agendamentoDAO.buscarAgendamentoPorVeiculoHistorico(codigo);
	}
	
	/**
	 * Método para buscar todos os agendamentos sem aprovação.
	 * Os agendamentos sem aprovação são agendamentos com CodUnidade diferente do usuário atual.
	 * 
	 * @return List<Agendamento> - Uma lista de agendamento encontrado no banco.
	 */
	public List<Agendamento> buscarAgendamentosParaAprovacao(long codUsuario) throws FrotasException{
		Usuario usuario = new UsuarioServicos().buscarPorCodigo(codUsuario);
		List<Agendamento> listaDeAgendamento = null;
		
		if(usuario != null)
			listaDeAgendamento = agendamentoDAO.buscarPorAgendamentosInativos(usuario.getCodUnidade());
		
		if(listaDeAgendamento.isEmpty())
			throw new FrotasException("Não existem agendamentos para este usuario");
		else
			return listaDeAgendamento;
	}

	/**
	 * @param codUsuario
	 * @return
	 * @throws FrotasException
	 */
	public List<Agendamento> buscarAgendamentosPorUsuario(long codUsuario) throws FrotasException {
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		List<Agendamento> listaDeAgendamento = agendamentoDAO.buscarPorUsuario(codUsuario);
		if(listaDeAgendamento.isEmpty())
			throw new FrotasException("Não existem agendamentos para este usuario");
		else{
			return listaDeAgendamento;
		}
	}
	
	public void alterarStatus(int status, long codAgendamento, long codUsuario) throws FrotasException {
		AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
		log.debug("Iniciando autorização do usuário");
		Usuario usuario = new UsuarioServicos().buscarPorCodigo(codUsuario);
		Agendamento agendamento = agendamentoDAO.buscarPorCodigo(codAgendamento);
		if(usuario != null && agendamento != null) {
			agendamento.setStatus(status);
			this.agendamentoDAO.atualizar(agendamento);
		} else {
			throw new FrotasException(msg.autorizacaoInvalida(""));
		}
	}

}