package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.Pessoa;

public class ConviteEventoDAOImpl extends DAOImpl<ConviteEvento, Integer> implements ConviteEventoDAO {

	/**
	 * Construtor padr�o
	 *
	 * @param entityManager Gerenciador das persist�ncias
	 * @author Ariel Molina 
	 */
	public ConviteEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	/**
	* Busca os Convites de Evento da Pessoa
	*
	* @param pessoa Pessoa que ser� utilizada para a busca
	* @return Convites da pessoa
	* @author Ariel Molina 
	*/
	@Override
	public List<ConviteEvento> buscarConviteEventoPorPessoa(Pessoa pessoa) {
		TypedQuery<ConviteEvento> query = em.createQuery("from ConviteEvento where pessoa = :pessoa",ConviteEvento.class);
		query.setParameter("pessoa", pessoa);
		return query.getResultList();
	}

}
