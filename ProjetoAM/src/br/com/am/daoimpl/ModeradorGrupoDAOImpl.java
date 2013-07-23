package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.ModeradorGrupoDAO;
import br.com.am.entity.ModeradorGrupo;

public class ModeradorGrupoDAOImpl extends DAOImpl<ModeradorGrupo, Integer> implements ModeradorGrupoDAO{

	public ModeradorGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
