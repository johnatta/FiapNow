package br.com.am.daoimpl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.am.dao.DAO;

public class DAOImpl<T,K> implements DAO<T,K> {

	@PersistenceContext(unitName="PROJETO_AM")
	protected EntityManager em;
	
	private Class<T> entityClass;
	
	@SuppressWarnings("all")
	public DAOImpl(EntityManager entityManager){
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
	}
	
	@Override
	public void insert(T entity) {
		em.getTransaction().begin();
		em.persist(entity);		
		em.getTransaction().commit();
	}

	@Override
	public void remove(T entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}

	@Override
	public void removeById(K id) {
		em.getTransaction().begin();
		T entity = em.find(entityClass,id);
		em.remove(entity);
		em.getTransaction().commit();
	}

	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}

	@Override
	public T searchByID(K id) {
		return em.find(entityClass, id);
	}

}
