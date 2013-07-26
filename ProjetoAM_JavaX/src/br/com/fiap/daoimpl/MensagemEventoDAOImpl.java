package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.entity.MensagemEvento;

public class MensagemEventoDAOImpl extends DAOImpl<MensagemEvento, Integer> implements MensagemEventoDAO{

	public MensagemEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
