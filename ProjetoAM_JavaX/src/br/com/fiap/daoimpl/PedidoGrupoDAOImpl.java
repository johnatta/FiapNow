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

	/**
	 * Busca os pedidos para entrar no Grupo da Pessoa
	 *
	 * @param pessoa Pessoa a ser utilizada na busca
	 * @return Pedidos de Grupo encontrados
	 * @author Ariel Molina
	 */
	@Override
	public List<PedidoGrupo> buscarPedidoGrupoPraPessoa(Pessoa pessoa) {
		TypedQuery<PedidoGrupo> query = em.createQuery(" from PedidoGrupo where grupo in (from Grupo where adm = :pes)",PedidoGrupo.class);
		query.setParameter("pes", pessoa);
		return query.getResultList();
	}

}
