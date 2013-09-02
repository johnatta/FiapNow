package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.entity.PedidoEvento;
import br.com.fiap.entity.Pessoa;

public class PedidoEventoDAOImpl extends DAOImpl<PedidoEvento, Integer> implements PedidoEventoDAO {

	public PedidoEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<PedidoEvento> buscarPedidosDeEventoPraPessoa(Pessoa pessoa) {
		TypedQuery<PedidoEvento> query = em.createQuery(" from PedidoEvento where evento in ( from Evento where adm = :pessoa)",PedidoEvento.class);
		query.setParameter("pessoa",pessoa);
		return query.getResultList();
	}
	
}
