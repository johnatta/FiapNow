package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.Pessoa;

public class ConviteEventoDAOImpl extends DAOImpl<ConviteEvento, Integer> implements ConviteEventoDAO {

	public ConviteEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<ConviteEvento> buscarConviteEventoPorPessoa(Pessoa pessoa) {
		TypedQuery<ConviteEvento> query = em.createQuery("from ConviteEvento where pessoa = :pessoa",ConviteEvento.class);
		query.setParameter("pessoa", pessoa);
		return query.getResultList();
	}

}
