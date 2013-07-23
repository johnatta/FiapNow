package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.ConviteEventoDAO;
import br.com.am.entity.ConviteEvento;

public class ConviteEventoDAOImpl extends DAOImpl<ConviteEvento, Integer> implements ConviteEventoDAO {

	public ConviteEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
