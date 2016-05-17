package br.com.fiescfrotas.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.fiescfrotas.constants.AgendamentoConstants;
import br.com.fiescfrotas.modelo.Agendamento;

public class AgendamentoDAO extends GenericDAO<Long, Agendamento> {

	public AgendamentoDAO() {
		super();
	}

	GregorianCalendar calendar = new GregorianCalendar();
	Date hoje = calendar.getTime();
/* 
	public Agendamento buscarPorCodigo(long codigo) {
		String select = "select u from " + Agendamento.class.getSimpleName() + " u where u.codigo = :codigo";
		return this.simpleEntityManager.getEntityManager().createQuery(select, Agendamento.class)
				.setParameter("codigo", codigo).getSingleResult();
	}*/

	public List<Agendamento> buscarAgendamentoPorVeiculo(long codVeiculo) {
		String select = "select a from " + Agendamento.class.getSimpleName() + " a where a.codVeiculo = :codVeiculo "
				+ "AND a.dataSaida >= :previsaoSaida "
				+ "AND a.status = :status";
		
		LocalDate localDate = LocalDate.now();
		List<Agendamento> listAgendamentos = this.simpleEntityManager.getEntityManager().createQuery(select, Agendamento.class)
				   .setParameter("codVeiculo", codVeiculo)
			   	   .setParameter("dataSaida", localDate.toDate())
				   .setParameter("status", AgendamentoConstants.ATIVO)
			   	   .getResultList();
		
		this.simpleEntityManager.close();
		
		return listAgendamentos;
	}
	
	public boolean buscarAgendamentoPorVeiculoHistorico(long codigo) {
		String sql = "SELECT count(v) FROM " + Agendamento.class.getSimpleName()+ " v WHERE v.codigo =:codigo"; 
		boolean historico = ((Long) this.simpleEntityManager.getEntityManager().createQuery(sql).setParameter("codigo",codigo).getSingleResult()) > 0;
		this.simpleEntityManager.close();
		return historico;
	}
	
	public List<Agendamento> buscarPorAgendamentosAtivos(){
		String SQL = "SELECT a FROM " + Agendamento.class.getSimpleName()
				 + " a WHERE a.status = :status and ";
		List<Agendamento> listAgendamentos = this.simpleEntityManager.getEntityManager()
				   .createQuery(SQL, Agendamento.class)
				   .setParameter("status", AgendamentoConstants.ATIVO)
				   .getResultList();
		return listAgendamentos;
	}
	
	public List<Agendamento> buscarPorUsuario(long codUsuario){
		String SQL = "SELECT a FROM " + Agendamento.class.getSimpleName()
				+ " a WHERE a.status = :status "
				+ "AND a.codUsuario = :codUsuario "
				+ "AND a.previsaoSaida > :hoje "
				+ "ORDER BY a.previsaoSaida, "
				+ "a.codVeiculo ";
		
		
		List<Agendamento> listAgendamentos = this.simpleEntityManager.getEntityManager()
				   .createQuery(SQL, Agendamento.class)
				   .setParameter("status", AgendamentoConstants.ATIVO)
				   .setParameter("codUsuario", codUsuario)
				   .setParameter("hoje", hoje)
				   .getResultList();
		return listAgendamentos;
	}
	
	public List<Agendamento> buscarPorAgendamentosInativos(long codUnidade){
		String SQL = "SELECT a FROM " + Agendamento.class.getSimpleName()
				 + " a WHERE a.status = :status "
				 + "AND a.previsaoSaida > :hoje "
				 + "AND a.usuario.codUnidade > :codUnidade";
		List<Agendamento> listAgendamentos = this.simpleEntityManager.getEntityManager()
				   .createQuery(SQL, Agendamento.class)
				   .setParameter("status", AgendamentoConstants.AGUARDANDO_APROVACAO)
				   .setParameter("hoje", hoje)
				   .setParameter("codUnidade", codUnidade)
				   .getResultList();
		return listAgendamentos;
	}

}