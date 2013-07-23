package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.UsuarioDAO;
import br.com.am.entity.Usuario;

public class UsuarioDAOImpl extends DAOImpl<Usuario, Integer> implements UsuarioDAO {

	public UsuarioDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
