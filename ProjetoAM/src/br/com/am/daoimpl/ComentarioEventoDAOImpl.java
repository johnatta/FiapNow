package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.ComentarioEventoDAO;
import br.com.am.entity.ComentarioEvento;

public class ComentarioEventoDAOImpl extends DAOImpl<ComentarioEvento, Integer> implements ComentarioEventoDAO {

	public ComentarioEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
