package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;

public class PessoaDAOImpl extends DAOImpl<Pessoa, Integer> implements PessoaDAO{

	/**
	 * Construtor padr�o
	 *
	 * @param entityManager Gerenciador das persist�ncias
	 * @author Ariel Molina 
	 */
	public PessoaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	/**
	 * Busca as informa��es da Pessoa
	 *
	 * @param codPessoa C�digo da pessoa a ser procurada
	 * @return Pessoa encontrada
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Pessoa buscarInformacoes(int codPessoa) {
		Query query = em.createQuery("from Pessoa pes where pes.codPessoa = :codPessoa");
		query.setParameter("codPessoa", codPessoa);
		return (Pessoa) query.getSingleResult();
	}

	/**
	 * Busca a Pessoa pelo Usuario
	 *
	 * @param codPessoa C�digo da pessoa a ser procurada
	 * @return Pessoa encontrada
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Pessoa buscarPorUsuario(Usuario usuario) {
		TypedQuery<Pessoa> query = em.createQuery("from Pessoa pes where pes.usuario = :codUsuario",Pessoa.class);
		query.setParameter("codUsuario", usuario);
		return query.getSingleResult();
		
	}

	/**
	 * Busca todas as Pessoas cadastradas
	 *
	 * @return Pessoas encontradas
	 * @author Graziele Vasconcelos
	 */
	@Override
	public List<Pessoa> buscarTodasPessoas() {
		TypedQuery<Pessoa> pessoa = (TypedQuery<Pessoa>) em.createQuery("from Pessoa",Pessoa.class);
		return pessoa.getResultList();
	}

	/**
	 * Busca uma pessoa pelo apelido
	 *
	 * @return pessoa encontrada a partir do apelido
	 * @author Ariel Molina
	 */
	@Override
	public List<Pessoa> buscarPorApelido(String apelido) {
		TypedQuery<Pessoa> query = (TypedQuery<Pessoa>) em.createQuery("from Pessoa pes where pes.apelido = :apel",Pessoa.class);
		query.setParameter("apel", apelido);
		return query.getResultList();
	}
}