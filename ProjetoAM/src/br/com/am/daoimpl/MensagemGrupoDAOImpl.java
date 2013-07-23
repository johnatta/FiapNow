package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.MensagemGrupoDAO;
import br.com.am.entity.MensagemGrupo;

public class MensagemGrupoDAOImpl extends DAOImpl<MensagemGrupo, Integer> implements MensagemGrupoDAO{

	public MensagemGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
