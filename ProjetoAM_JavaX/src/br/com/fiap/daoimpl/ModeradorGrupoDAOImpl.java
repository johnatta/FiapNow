package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.Pessoa;

public class ModeradorGrupoDAOImpl extends DAOImpl<ModeradorGrupo, Integer> implements ModeradorGrupoDAO{

	public ModeradorGrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	/**
	 * Busca os Moderadores do Grupo
	 *
	 * @param codGrupo Código do Grupo
	 * @return Moderadores encontrados
	 * @author Graziele Vasconcelos
	 */
	public List<Pessoa> buscarModeradoresDoGrupo(int codGrupo) {
		TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery ("SELECT * FROM AM_PESSOA  WHERE cod_pessoa IN (SELECT cod_moderador_pessoa  FROM AM_MODERADOR_GRUPO WHERE cod_moderador_grupo =  :codGrupo)", Pessoa.class);
		p.setParameter("codGrupo", codGrupo);
		return p.getResultList();
	}
	
	/**
	 * Busca os seis Moderadores do Grupo
	 *
	 * @param codGrupo Código do Grupo
	 * @return Moderadores encontrados
	 * @author Graziele Vasconcelos
	 */
	public List<Pessoa> buscarModeradoresDoGrupoRowNum(int codGrupo) {
		TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery ("SELECT * FROM AM_PESSOA  WHERE cod_pessoa IN (SELECT cod_moderador_pessoa  FROM AM_MODERADOR_GRUPO WHERE cod_moderador_grupo =  :codGrupo and rownum <=6)", Pessoa.class);
		p.setParameter("codGrupo", codGrupo);
		return p.getResultList();
	}

	/**
	 * Busca um Moderador do grupo.   
	 *
	 * @param codGrupo Código do Grupo
	 * @param codGrupo Código da Pessoa
	 * @return Moderador encontrado
	 * @author Graziele Vasconcelos
	 */
	@Override
	public ModeradorGrupo buscarModeradorGrupo(int codGrupo, int codPessoa) {
		TypedQuery<ModeradorGrupo> query = (TypedQuery<ModeradorGrupo>) em.createNativeQuery("from ModeradorGrupo mg where mg.grupo.codGrupo = :codGrupo and mg.pessoa.codPessoa = :codPessoa", ModeradorGrupo.class);
		query.setParameter("codGrupo", codGrupo);
		query.setParameter("codPessoa", codPessoa);
		return query.getSingleResult();
	}
	
}
