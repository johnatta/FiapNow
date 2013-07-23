package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.MensagemEventoDAO;
import br.com.am.entity.MensagemEvento;

public class MensagemEventoDAOImpl extends DAOImpl<MensagemEvento, Integer> implements MensagemEventoDAO{

	public MensagemEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
