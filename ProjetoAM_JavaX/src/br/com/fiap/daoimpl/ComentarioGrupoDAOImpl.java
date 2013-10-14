package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.entity.ComentarioGrupo;

public class ComentarioGrupoDAOImpl extends DAOImpl<ComentarioGrupo, Integer> implements ComentarioGrupoDAO{

	/**
	 * Construtor padr�o
	 *
	 * @param entityManager Gerenciador das persist�ncias
	 * @author Ariel Molina 
	 */
	public ComentarioGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
