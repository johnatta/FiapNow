package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.EventoDAO;
import br.com.fiap.entity.ComentarioEvento;
import br.com.fiap.entity.Evento;

public class EventoDAOImpl extends DAOImpl<Evento, Integer> implements EventoDAO{

	public EventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Evento buscarPeloCodigo(int codEvento) {
		TypedQuery<Evento> query = em.createQuery("from Evento where codEvento = :cod", Evento.class);
		query.setParameter("cod", codEvento);
		return query.getSingleResult();
	}

	//Still doesn't work - Ariel
	@Override
	public List<ComentarioEvento> buscarComentariosPeloEvento(int codEvento) {
		TypedQuery<ComentarioEvento> query = em.createQuery("", ComentarioEvento.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

}
