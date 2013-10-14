package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.entity.Usuario;

public class UsuarioDAOImpl extends DAOImpl<Usuario, Integer> implements UsuarioDAO {

	/**
	 * Construtor padrão
	 *
	 * @param entityManager Gerenciador das persistências
	 * @author Ariel Molina 
	 */
	public UsuarioDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	/**
	 * Busca o Usuario por email e senha
	 *
	 * @param email Email a ser utilizado na busca
	 * @param senha Senha a ser utilizada na busca
	 * @return Usuario encontrado
	 * @author Ariel Molina
	 */
	@Override
	public Usuario buscarEmailESenha(String email, String senha) {
		TypedQuery<Usuario> query = em.createQuery("from Usuario where email = :em and senha = :pw",Usuario.class);
		query.setParameter("em", email);
		query.setParameter("pw", senha);
		Usuario usuarioRetorno;
		try {
			usuarioRetorno = query.getSingleResult();
		} catch (Exception e) {
			usuarioRetorno = null;
		}
		return usuarioRetorno;
	}

	/**
	 * Busca o Usuario por email
	 *
	 * @param email Email a ser utilizado na busca
	 * @return Usuario encontrado
	 * @author Ariel Molina
	 */
	@Override
	public Usuario buscarPorEmail(String email) {
		TypedQuery<Usuario> query = em.createQuery("from Usuario where email = :em",Usuario.class);
		query.setParameter("em", email);
		Usuario usuarioRetorno;
		try {
			usuarioRetorno = query.getSingleResult();
		} catch (Exception e) {
			usuarioRetorno = null;
		}
		return usuarioRetorno;
	}

}
