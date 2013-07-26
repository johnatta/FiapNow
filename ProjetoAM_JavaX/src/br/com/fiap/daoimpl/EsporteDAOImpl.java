package br.com.fiap.daoimpl;

import javax.persistence.EntityManager;

import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.entity.Esporte;

public class EsporteDAOImpl extends DAOImpl<Esporte, Integer> implements EsporteDAO {
	public EsporteDAOImpl(EntityManager em) {
		super(em);
	}
}
