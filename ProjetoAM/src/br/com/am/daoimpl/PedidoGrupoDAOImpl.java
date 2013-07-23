package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.PedidoGrupoDAO;
import br.com.am.entity.PedidoGrupo;

public class PedidoGrupoDAOImpl extends DAOImpl<PedidoGrupo, Integer> implements PedidoGrupoDAO {

	public PedidoGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
