package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.EventoDAO;
import br.com.am.entity.Evento;

public class EventoDAOImpl extends DAOImpl<Evento, Integer> implements EventoDAO{

	public EventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
