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
	 * Busca seis membros do Grupo
	 *
	 * @param codGrupo C�digo do Grupo ser procurada
	 * @return Membros encontrados
	 * @author Johnatta Santos
	 */
	@Override
	public List<Pessoa> buscarMembrosDoGrupoRow(int codGrupo) {
		@SuppressWarnings("unchecked")
		TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery("SELECT * FROM AM_PESSOA WHERE cod_pessoa IN (SELECT cod_pessoa  FROM AM_PESSOA_GRUPO  WHERE cod_grupo = :codGrupo and rownum <=6)", Pessoa.class);
		p.setParameter("codGrupo", codGrupo);
		return p.getResultList();
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