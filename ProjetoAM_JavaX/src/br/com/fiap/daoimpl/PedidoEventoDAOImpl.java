package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.entity.PedidoEvento;

public class PedidoEventoDAOImpl extends DAOImpl<PedidoEvento, Integer> implements PedidoEventoDAO {

	public PedidoEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
