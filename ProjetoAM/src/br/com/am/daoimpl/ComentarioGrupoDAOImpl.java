package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.ComentarioGrupoDAO;
import br.com.am.entity.ComentarioGrupo;

public class ComentarioGrupoDAOImpl extends DAOImpl<ComentarioGrupo, Integer> implements ComentarioGrupoDAO{

	public ComentarioGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
