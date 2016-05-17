package br.com.fiescfrotas.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

public class GenericDAO<Id, T> {

	/**
	 * Classe para encapsular o EntityManager e só expor o essencial em mêtodos,
	 * como por exemplo, commit, rollback, beginTransaction, etc.
	 */
	protected SimpleEntityManager simpleEntityManager;

	/**
	 * Construtor padrão
	 */
	public GenericDAO() {
//		SimpleEntityManager simpleEntityManager = new SimpleEntityManager();
		this.simpleEntityManager = simpleEntityManager.getInstance();
	}

	public GenericDAO(SimpleEntityManager entityManager) {
		this.simpleEntityManager = entityManager;
	}
	
	/**
	 * Método para inserir entity específico.
	 * 
	 * @param entity
	 *            Objeto - Objeto entity
	 * @return void
	 */
	public void inserir(T entity) {
		try {
			simpleEntityManager.beginTransaction();
			simpleEntityManager.persist(entity);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		} finally {
			simpleEntityManager.close();
		}
	}
	
	/**
	 * Método para buscar todos os entity - Sem filtro.
	 * 
	 * @return List<entity> - Uma lista das entity encontradas no banco.
	 */
	@SuppressWarnings("unchecked")
	public List<T> buscarTodos() {
		List<T> listaEntity = new ArrayList<T>();
		try {
			Query query = simpleEntityManager.getEntityManager().createQuery(
					"SELECT e FROM " + this.getTypeClass().getName() + " e");
			listaEntity = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			simpleEntityManager.close();
		}
		return listaEntity;
	}
	
	/**
	 * Método para buscar uma entity específico.
	 * 
	 * @param codigo
	 *            codigo da entity
	 * @return Objeto entity
	 */
	@SuppressWarnings("unchecked")
	public T buscarPorCodigo(long codigo) {
		T entity = null;
		try {
			entity = (T) simpleEntityManager.find(getTypeClass(), codigo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			simpleEntityManager.close();
		}
		return entity;
	}
	
	/**
	 * Método para atualizar entity.
	 * 
	 * @param entity
	 *            Entity - Objeto entity
	 * @return void
	 */
	public void atualizar(T entity) {
		try {
			simpleEntityManager.beginTransaction();
			simpleEntityManager.merge(entity);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		} finally {
			simpleEntityManager.close();
		}
	}
	
	/**
	 * Método para remover uma entity específica.
	 * 
	 * @param codigo
	 *            codigo da entity
	 * @return void
	 */
	@SuppressWarnings("unchecked")
	public void remover(long codigo) {
		T entity = null;
		try {
			simpleEntityManager.beginTransaction();
			entity = (T) simpleEntityManager.find(getTypeClass(), codigo);
			simpleEntityManager.remove(entity);
			simpleEntityManager.commit();
		} catch (Exception e) {
			e.printStackTrace();
			simpleEntityManager.rollBack();
		} finally {
			simpleEntityManager.close();
		}
	}

	/**
	 * Método para retornar o tipo da entity.
	 * 
	 * @return clazz
	 */
	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		return clazz;
	}
}