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
	 * Construtor padrão
	 *
	 * @param entityManager Gerenciador das persistências
	 * @author Ariel Molina 
	 */
	public PessoaDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}
	
	/**
	 * Busca as informações da Pessoa
	 *
	 * @param codPessoa Código da pessoa a ser procurada
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
	 * @param codPessoa Código da pessoa a ser procurada
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
}