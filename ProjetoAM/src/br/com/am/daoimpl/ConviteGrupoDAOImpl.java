package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.ConviteGrupoDAO;
import br.com.am.entity.ConviteGrupo;

public class ConviteGrupoDAOImpl extends DAOImpl<ConviteGrupo, Integer> implements ConviteGrupoDAO {

	public ConviteGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
