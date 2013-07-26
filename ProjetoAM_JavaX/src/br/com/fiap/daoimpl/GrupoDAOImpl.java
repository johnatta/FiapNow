package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.entity.Grupo;

public class GrupoDAOImpl extends DAOImpl<Grupo, Integer> implements GrupoDAO {

	public GrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
