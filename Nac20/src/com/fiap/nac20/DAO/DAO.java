package com.fiap.nac20.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;



public class DAO<T> {
	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public T consultarProduto(int codProduto) {
		EntityManager em = JPAUtil.getEntityManager();
		return em.find(this.classe, codProduto);
	}
	public void adicionarProduto(T produtoTO) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(produtoTO);
		em.getTransaction().commit();
		em.close();
	}
	public List<T> listarProdutos() {
		EntityManager em = JPAUtil.getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		List<T> produtos = em.createQuery(query).getResultList();
		em.close();
		return produtos;
	}
	
	public void atualizarProduto(T produtoTO){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(produtoTO);
		em.getTransaction().commit();
		em.close();
	}
	
	public void removerProduto(T produtoTO){
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(produtoTO));
		em.getTransaction().commit();
		em.close();
	}
	public List<String> buscarTodasDescricoesProdutos(String descricao){
		EntityManager em = JPAUtil.getEntityManager();
		List<String> descricoes = em.createQuery(
				"select p.descricao from ProdutoTO p where lower(p.descricao) like :descricao", String.class)
		.setParameter("descricao", "%" + descricao.toLowerCase() + "%")
		.getResultList();
		em.close();
		return descricoes;	
	}
}
