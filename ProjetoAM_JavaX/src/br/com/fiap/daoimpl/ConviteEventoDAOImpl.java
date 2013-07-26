package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.entity.ConviteEvento;

public class ConviteEventoDAOImpl extends DAOImpl<ConviteEvento, Integer> implements ConviteEventoDAO {

	public ConviteEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
