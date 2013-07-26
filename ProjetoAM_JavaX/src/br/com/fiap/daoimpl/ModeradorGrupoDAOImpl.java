package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.entity.ModeradorGrupo;

public class ModeradorGrupoDAOImpl extends DAOImpl<ModeradorGrupo, Integer> implements ModeradorGrupoDAO{

	public ModeradorGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
