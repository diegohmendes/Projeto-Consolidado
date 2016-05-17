package br.com.fiescfrotas.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SimpleEntityManager {
	private static EntityManagerFactory factory;
	private EntityManager entityManager;

	public static SimpleEntityManager getInstance() {
		return new SimpleEntityManager("DSFiescFrotas");
	}

	private SimpleEntityManager(EntityManagerFactory factory) {
		SimpleEntityManager.factory = factory;
		this.entityManager = factory.createEntityManager();
	}

	private SimpleEntityManager(String persistenceUnitName) {
		SimpleEntityManager.factory = Persistence.createEntityManagerFactory(persistenceUnitName);
		this.entityManager = factory.createEntityManager();
	}

	public void beginTransaction() {
		getEntityManager().getTransaction().begin();
	}

	public void commit() {
		getEntityManager().getTransaction().commit();
	}

	public void persist(Object entity) {
		getEntityManager().persist(entity);
	}

	public void merge(Object entity) {
		getEntityManager().merge(entity);
	}

	public void remove(Object entity) {
		getEntityManager().remove(entity);
	}

	public Object find(Class<?> object, long id) {
		return getEntityManager().find(object, id);
	}

	public void close() {
		getEntityManager().close();
//		entityManager = null;
//		factory.close();
	}

	public void rollBack() {
		getEntityManager().getTransaction().rollback();
	}

	public EntityManager getEntityManager() {
		if(entityManager == null || !entityManager.isOpen()){
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
}
