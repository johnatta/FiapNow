package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.ComentarioEventoDAO;
import br.com.fiap.entity.ComentarioEvento;

public class ComentarioEventoDAOImpl extends DAOImpl<ComentarioEvento, Integer> implements ComentarioEventoDAO {

	/**
	 * Construtor padr�o
	 *
	 * @param entityManager Gerenciador das persist�ncias
	 * @author Ariel Molina 
	 */
	public ComentarioEventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

}
