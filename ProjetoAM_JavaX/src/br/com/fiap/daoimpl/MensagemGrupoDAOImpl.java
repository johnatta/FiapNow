package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.entity.MensagemGrupo;

public class MensagemGrupoDAOImpl extends DAOImpl<MensagemGrupo, Integer> implements MensagemGrupoDAO{

	public MensagemGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
