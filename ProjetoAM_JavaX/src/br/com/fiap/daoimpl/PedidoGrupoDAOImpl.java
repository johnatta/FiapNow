package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.entity.PedidoGrupo;

public class PedidoGrupoDAOImpl extends DAOImpl<PedidoGrupo, Integer> implements PedidoGrupoDAO {

	public PedidoGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
