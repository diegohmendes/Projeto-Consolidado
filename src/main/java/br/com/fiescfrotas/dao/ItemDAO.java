package br.com.fiescfrotas.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.fiescfrotas.modelo.Item;

public class ItemDAO extends GenericDAO<Long, Item> {
	
	public ItemDAO() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public List<Item> buscarTodosPelaManutencao(long codManutencao) {
		List<Item> listaitem = new ArrayList<Item>();
		try {
			Query query = simpleEntityManager.getEntityManager().createQuery(
					"select i from " +Item.class.getSimpleName() +" i where i.codManutencao = :p1");
			query.setParameter("p1", codManutencao);
			listaitem = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			simpleEntityManager.close();
		}
		return ((listaitem.isEmpty()) ? null : listaitem);
	}

}
