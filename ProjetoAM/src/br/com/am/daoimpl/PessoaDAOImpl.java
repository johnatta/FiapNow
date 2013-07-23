package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.PessoaDAO;
import br.com.am.entity.Pessoa;



public class PessoaDAOImpl extends DAOImpl<Pessoa, Integer> implements PessoaDAO {

	public PessoaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}



