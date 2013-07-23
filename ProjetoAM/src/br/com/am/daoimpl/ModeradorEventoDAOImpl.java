package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.ModeradorEventoDAO;
import br.com.am.entity.ModeradorEvento;

public class ModeradorEventoDAOImpl extends DAOImpl<ModeradorEvento, Integer> implements ModeradorEventoDAO{

	public ModeradorEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
