package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.PedidoEventoDAO;
import br.com.am.entity.PedidoEvento;

public class PedidoEventoDAOImpl extends DAOImpl<PedidoEvento, Integer> implements PedidoEventoDAO {

	public PedidoEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
