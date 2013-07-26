package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.entity.Pessoa;

public class PessoaDAOImpl extends DAOImpl<Pessoa, Integer> implements PessoaDAO{

	public PessoaDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
