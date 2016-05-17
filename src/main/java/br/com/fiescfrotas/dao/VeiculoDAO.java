package br.com.fiescfrotas.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.fiescfrotas.constants.VeiculoConstants;
import br.com.fiescfrotas.modelo.Veiculo;

public class VeiculoDAO extends GenericDAO<Long, Veiculo> {
	public VeiculoDAO() {
		super();
	}
	
	public List<Veiculo> buscarPorVeiculosAtivos(){
		String SQL = "SELECT u FROM " +Veiculo.class.getSimpleName()+ " u WHERE u.status <> :status";
		return this.simpleEntityManager.getEntityManager().createQuery(SQL, Veiculo.class).setParameter("status", VeiculoConstants.ATIVO).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Veiculo> buscarTodosUnicos(long codigo, String placa, int patrimonio) {
		String sql = "SELECT v FROM " + Veiculo.class.getSimpleName() + " v WHERE (v.placa = :placa OR v.patrimonio = :patrimonio) and v.codigo <> :codigo";
		
		return this.simpleEntityManager.getEntityManager().createQuery(sql).setParameter("placa", placa).setParameter("patrimonio", patrimonio)
				.setParameter("codigo", codigo).getResultList();
	}
	
	public List<Veiculo> buscarVeiculosDisponiveis(){
		GregorianCalendar calendar = new GregorianCalendar();
		Date hoje = calendar.getTime();

		String SQL = " SELECT v FROM " + Veiculo.class.getSimpleName() + " v "
				+ " WHERE v.status = :status";
		return this.simpleEntityManager.getEntityManager()
									   .createQuery(SQL, Veiculo.class)
									   .setParameter("status", VeiculoConstants.ATIVO)
									   .getResultList();
	}
}