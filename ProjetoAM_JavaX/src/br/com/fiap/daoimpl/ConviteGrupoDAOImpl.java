package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.Pessoa;

public class ConviteGrupoDAOImpl extends DAOImpl<ConviteGrupo, Integer> implements ConviteGrupoDAO {

	public ConviteGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<ConviteGrupo> buscarConviteGrupoPorPessoa(Pessoa pessoa) {
		TypedQuery<ConviteGrupo> query = em.createQuery("from ConviteGrupo where pessoa = :pes",ConviteGrupo.class);
		query.setParameter("pes", pessoa);
		return query.getResultList();
	}

}
