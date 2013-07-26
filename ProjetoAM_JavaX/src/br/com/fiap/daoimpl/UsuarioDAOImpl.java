package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.Usuario;

public class UsuarioDAOImpl extends DAOImpl<Usuario, Integer> implements UsuarioDAO {

	public UsuarioDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
