package br.com.fiap.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
	public List<Grupo> buscaGruposDoUsuario(int codPessoa) {
		TypedQuery<Grupo> q = (TypedQuery<Grupo>) em.createNativeQuery("select * from am_grupo gru where gru.cod_grupo in (select pg.cod_grupo from am_pessoa_grupo pg where pg.cod_pessoa = ? and rownum <= 3)", Grupo.class);
		q.setParameter(1, codPessoa);
		return q.getResultList();
	}

	@Override
	public Grupo buscaInfoBasicas(int codGrupo) {
		TypedQuery<Grupo> query = em.createQuery("from Grupo where cod_grupo = :cod",Grupo.class);
		query.setParameter("cod", codGrupo);
		Grupo grupo = query.getSingleResult();
		
		Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo a where a.cod_grupo = :cod");
		queryQtd.setParameter("cod", codGrupo);
		
		BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
		grupo.setQuantidade(qtd);
		return grupo;
	}

	@Override
	public List<Grupo> buscaGruposPorUsuario(int codPessoa) {
		ArrayList<Integer> codigos  = new ArrayList<Integer>(); 
		TypedQuery<Grupo> query = (TypedQuery<Grupo>) em.createNativeQuery("select gru.* from am_grupo gru where gru.cod_grupo in (select cod_grupo from am_pessoa_grupo where cod_pessoa = ?)",Grupo.class);
		query.setParameter(1, codPessoa);
		List<Grupo> grupos = query.getResultList();
		
		for (Grupo grupo : grupos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
			queryQtd.setParameter("codGrupo", grupo.getCodGrupo());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			grupo.setQuantidade(qtd);
		}
		return grupos;
	}
}
