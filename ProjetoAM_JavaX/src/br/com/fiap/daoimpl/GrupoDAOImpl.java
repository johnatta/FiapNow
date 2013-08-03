package br.com.fiap.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.entity.Grupo;

public class GrupoDAOImpl extends DAOImpl<Grupo, Integer> implements GrupoDAO {

	public GrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Grupo> buscaGruposPorUsuario(int codPessoa) {
		TypedQuery<Grupo> query = em.createQuery("select codgrupo, nomegrupo from Grupo g where g.codGrupo in (select cod_grupo from am_pessoa_grupo pg where pg.cod_pessoa = :codPessoa)",Grupo.class);
		query.setFirstResult(0);
		query.setMaxResults(3); 
		query.setParameter("codPessoa",codPessoa);
		return query.getResultList();
	}

	@Override
	public Grupo buscaInfoBasicas(int codGrupo) {
		Query query = em.createQuery("select gru.cod_grupo, gru.nome_grupo, gru.img_grupo, (select count(pg) from am_pessoa_grupo pg where pg.cod_grupo = gru.cod_grupo) as nummembros from Grupo gru order by nummembros");
		query.setParameter("codGrupo", codGrupo);
		return (Grupo) query.getSingleResult();
	}

	@Override
	public List<Grupo> buscaGruposDoUsuario(int codPessoa) {
		TypedQuery<Grupo> query = em.createQuery("select gru.cod_grupo, gru.nome_grupo, gru.img_grupo, (" +
				"select count(pg) from am_pessoa_grupo pg where pg.cod_grupo = gru.cod_grupo) as nummembros " +
				"from Grupo gru where gru.cod_grupo in (select cod_grupo from am_pessoa_grupo where " +
				"cod_pessoa = :codPessoa) order by nummembros",Grupo.class);
		query.setParameter("codPessoa", codPessoa);
		return query.getResultList();
	}

}
