package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;

public class EsporteDAOImpl extends DAOImpl<Esporte, Integer> implements EsporteDAO {
	public EsporteDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public List<Esporte> buscarTodosEsportes() {
		TypedQuery<Esporte> query = em.createQuery("from Esporte", Esporte.class);
		return query.getResultList();
	}

	@Override
	public Esporte buscarPorNome(String nome) {
		TypedQuery<Esporte> query = em.createQuery("from Esporte e where upper(e.nome) like upper(:nome)",Esporte.class);
		query.setParameter("nome", "%"+nome+"%");
		return query.getSingleResult();
	}
}
