package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.GrupoDAO;
import br.com.am.entity.Grupo;

public class GrupoDAOImpl extends DAOImpl<Grupo, Integer> implements GrupoDAO {

	public GrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
