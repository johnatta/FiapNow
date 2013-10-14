package br.com.fiap.daoimpl;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import br.com.fiap.dao.DAO;

public class DAOImpl<T, K> implements DAO<T,K> {
	protected EntityManager em;
	private Class<T> entityClass;

	/**
	 * Construtor padrão
	 *
	 * @param entityManager Gerenciador das persistências
	 * @author Ariel Molina 
	 */
	public DAOImpl(EntityManager entityManager) {
		em = entityManager;
		entityClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];		
	}
	
	/**
	 * Insere a entidade
	 *
	 * @param entity Entidade que será persistida
	 * @author Graziele Vasconcelos
	 */
	@Override
	public void insert(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
	}
	
	/**
	 * Remove a entidade
	 *
	 * @param entity Entidade que será removida
	 * @author Graziele Vasconcelos 
	 */
	@Override
	public void remove(T entity) {
		em.getTransaction().begin();
		em.remove(entity);
		em.getTransaction().commit();
	}
	
	/**
	 * Remove a entidade pelo id
	 *
	 * @param id Id da entidade que será removida
	 * @author Graziele Vasconcelos 
	 */
	@Override
	public void removeById(K id) {
		em.getTransaction().begin();
		T entity = em.find(entityClass, id);
		em.remove(entity);
		em.getTransaction().commit();

	}
	
	/**
	 * Atualiza a entidade
	 *
	 * @param entity Entidade que será atualizada
	 * @author Graziele Vasconcelos 
	 */
	@Override
	public void update(T entity) {
		em.getTransaction().begin();
		em.merge(entity);
		em.getTransaction().commit();
	}
	
	/**
	 * Busca a entidade pelo id
	 *
	 * @param entity Id da entidade que será buscada
	 * @author Graziele Vasconcelos 
	 */
	@Override
	public T searchByID(K id) {
		return em.find(entityClass, id);
	}

	/**
	 * Insere a entidade e retorna a mesma persistida
	 *
	 * @param entity Entidade que será persistida
	 * @return entity Entidade persistida
	 * @author Graziele Vasconcelos 
	 */
	@Override
	public T insertEntity(T entity) {
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		return entity;
	}

}
