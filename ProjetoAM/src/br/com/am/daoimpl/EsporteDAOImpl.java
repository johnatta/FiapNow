package br.com.am.daoimpl;

import javax.persistence.EntityManager;

import br.com.am.dao.EsporteDAO;
import br.com.am.entity.Esporte;

public class EsporteDAOImpl extends DAOImpl<Esporte, Integer> implements EsporteDAO {
	public EsporteDAOImpl(EntityManager em) {
		super(em);
	}
}
