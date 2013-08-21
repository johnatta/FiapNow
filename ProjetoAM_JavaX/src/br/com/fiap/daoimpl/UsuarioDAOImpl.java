package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.Usuario;

public class UsuarioDAOImpl extends DAOImpl<Usuario, Integer> implements UsuarioDAO {

	public UsuarioDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Usuario buscarEmailESenha(String email, String senha) {
		TypedQuery<Usuario> query = em.createQuery("from Usuario where email = :em and senha = :pw",Usuario.class);
		query.setParameter("em", email);
		query.setParameter("pw", senha);
		return query.getSingleResult();
	}

}
