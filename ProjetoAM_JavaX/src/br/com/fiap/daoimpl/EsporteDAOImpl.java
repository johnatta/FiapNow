package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.entity.Esporte;

public class EsporteDAOImpl extends DAOImpl<Esporte, Integer> implements EsporteDAO {
	public EsporteDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Esporte> buscarTodosEsportes() {
		TypedQuery<Esporte> query = em.createQuery("select codEsporte, nomegrupo from Esporte", Esporte.class);
		return query.getResultList();
	}
}
