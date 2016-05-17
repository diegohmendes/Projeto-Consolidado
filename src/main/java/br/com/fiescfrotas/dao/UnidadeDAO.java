package br.com.fiescfrotas.dao;

import java.util.List;

import br.com.fiescfrotas.modelo.Unidade;

public class UnidadeDAO extends GenericDAO<Long, Unidade> {

	public List<Unidade> buscarTodasUnidades() {
		String SQL = "SELECT u FROM " + Unidade.class.getSimpleName() + " u ";
		return this.simpleEntityManager.getEntityManager().createQuery(SQL, Unidade.class)
				.getResultList();
	}

}
