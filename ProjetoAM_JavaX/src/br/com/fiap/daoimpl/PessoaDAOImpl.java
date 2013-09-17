package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;

public class PessoaDAOImpl extends DAOImpl<Pessoa, Integer> implements PessoaDAO{

	public PessoaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	@Override
	public Pessoa buscarInformacoes(int codPessoa) {
		Query query = em.createQuery("from Pessoa pes where pes.codPessoa = :codPessoa");
		query.setParameter("codPessoa", codPessoa);
		return (Pessoa) query.getSingleResult();
	}

	@Override
	public List<Pessoa> buscarMembrosDoGrupo(int codGrupo) {
		@SuppressWarnings("unchecked")
		TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery("SELECT * FROM AM_PESSOA WHERE cod_pessoa IN (SELECT cod_pessoa  FROM AM_PESSOA_GRUPO  WHERE cod_grupo = :codGrupo)", Pessoa.class);
		p.setParameter("codGrupo", codGrupo);
		return p.getResultList();

	}

	@Override
	public Pessoa buscarPorUsuario(Usuario usuario) {
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa pes where pes.usuario = :codUsuario",Pessoa.class);
		query.setParameter("codUsuario", usuario);
		return query.getSingleResult();
		
	}
}