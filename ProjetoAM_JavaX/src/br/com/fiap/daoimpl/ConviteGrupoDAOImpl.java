package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.Pessoa;

public class ConviteGrupoDAOImpl extends DAOImpl<ConviteGrupo, Integer> implements ConviteGrupoDAO {

	/**
	 * Construtor padr�o
	 *
	 * @param entityManager Gerenciador das persist�ncias
	 * @author Ariel Molina 
	 */
	public ConviteGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	/**
	* Busca os Convites de Grupo da Pessoa
	*
	* @param pessoa Pessoa que ser� utilizada para a busca
	* @return Convites da pessoa
	* @author Ariel Molina 
	*/
	@Override
	public List<ConviteGrupo> buscarConviteGrupoPorPessoa(Pessoa pessoa) {
		TypedQuery<ConviteGrupo> query = em.createQuery("from ConviteGrupo where pessoa = :pes",ConviteGrupo.class);
		query.setParameter("pes", pessoa);
		return query.getResultList();
	}

}
