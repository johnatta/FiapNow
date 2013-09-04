package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;

public class PedidoGrupoDAOImpl extends DAOImpl<PedidoGrupo, Integer> implements PedidoGrupoDAO {

	public PedidoGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<PedidoGrupo> buscarPedidoGrupoPraPessoa(Pessoa pessoa) {
		TypedQuery<PedidoGrupo> query = em.createQuery(" from PedidoGrupo where pessoa = :pes",PedidoGrupo.class);
		query.setParameter("pes", pessoa);
		return query.getResultList();
	}

}
