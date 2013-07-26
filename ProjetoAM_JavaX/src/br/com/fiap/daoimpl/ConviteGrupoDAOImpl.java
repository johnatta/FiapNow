package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.entity.ConviteGrupo;

public class ConviteGrupoDAOImpl extends DAOImpl<ConviteGrupo, Integer> implements ConviteGrupoDAO {

	public ConviteGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
