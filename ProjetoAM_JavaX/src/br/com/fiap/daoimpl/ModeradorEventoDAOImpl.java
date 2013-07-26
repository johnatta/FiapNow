package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ModeradorEventoDAO;
import br.com.fiap.entity.ModeradorEvento;

public class ModeradorEventoDAOImpl extends DAOImpl<ModeradorEvento, Integer> implements ModeradorEventoDAO{

	public ModeradorEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
