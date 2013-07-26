package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EventoDAO;
import br.com.fiap.entity.Evento;

public class EventoDAOImpl extends DAOImpl<Evento, Integer> implements EventoDAO{

	public EventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
