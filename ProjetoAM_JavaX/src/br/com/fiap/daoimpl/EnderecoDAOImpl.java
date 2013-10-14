package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EnderecoDAO;
import br.com.fiap.entity.Endereco;

public class EnderecoDAOImpl extends DAOImpl<Endereco, Integer> implements EnderecoDAO {

	/**
	 * Construtor padrão
	 *
	 * @param entityManager Gerenciador das persistências
	 * @author Ariel Molina 
	 */
	public EnderecoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
