package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.EnderecoDAO;
import br.com.am.entity.Endereco;

public class EnderecoDAOImpl extends DAOImpl<Endereco, Integer> implements EnderecoDAO {

	public EnderecoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
