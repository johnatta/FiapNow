package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.entity.Pessoa;

public class PessoaDAOImpl extends DAOImpl<Pessoa, Integer> implements PessoaDAO{

	public PessoaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Pessoa buscarInformacoes(int codPessoa) {
		Query query = em.createQuery("from Pessoa pes where pes.cod_pessoa_susuario = :codPessoa");
		query.setParameter("codPessoa", codPessoa);
		return (Pessoa) query.getSingleResult();
	}
	
	//Still doesn't work - Ariel
	@Override
	public List<Pessoa> buscarMembrosPorEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa where codPessoa in " +
				"(select codPessoa from Evento where codEvento = :cod)",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

	//Still doesn't work - Ariel
	@Override
	public List<Pessoa> buscarModeradoresDoEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}



}
