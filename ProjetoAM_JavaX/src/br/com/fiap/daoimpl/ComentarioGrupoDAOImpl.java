package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.entity.ComentarioGrupo;

public class ComentarioGrupoDAOImpl extends DAOImpl<ComentarioGrupo, Integer> implements ComentarioGrupoDAO{

	/**
	 * Construtor padrão
	 *
	 * @param entityManager Gerenciador das persistências
	 * @author Ariel Molina 
	 */
	public ComentarioGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
