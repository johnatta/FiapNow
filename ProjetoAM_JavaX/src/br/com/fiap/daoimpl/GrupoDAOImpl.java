package br.com.fiap.daoimpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;

public class GrupoDAOImpl extends DAOImpl<Grupo, Integer> implements GrupoDAO {

	public GrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Grupo> buscaGruposDoUsuario(int codPessoa) {
		TypedQuery<Grupo> q = (TypedQuery<Grupo>) em.createNativeQuery("select * from am_grupo gru where gru.cod_grupo in (select pg.cod_grupo from am_pessoa_grupo pg where pg.cod_pessoa = ?)", Grupo.class);
		q.setParameter(1, codPessoa);
		return q.getResultList();
	}

	@Override
	public List<Grupo> buscarGrupos() {
		TypedQuery<Grupo> query = em.createQuery("from Grupo",Grupo.class);
		List<Grupo> grupos = query.getResultList();
		for (Grupo g: grupos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
			queryQtd.setParameter("codGrupo", g.getCodGrupo());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			g.setQuantidade(qtd);
		}
		return grupos;
	}

	@Override
	public List<Grupo> consultaMeusGrupos(int codPessoa) {
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
	
	@Override
	public List<Pessoa> buscarUsuariosParaAdicionarAoGrupo(int codGrupo) {
		@SuppressWarnings("unchecked")
		TypedQuery<Pessoa> query = (TypedQuery<Pessoa>) em.createNativeQuery("SELECT * FROM AM_PESSOA P where cod_pessoa not in" +
				"(select cod_pessoa from am_pessoa_grupo where cod_grupo = :codGrupo)",Pessoa.class);
		query.setParameter("codGrupo",codGrupo);
		return query.getResultList();
		
	}

	@Override
	public Grupo buscarInfoGrupo(int codGrupo) {
		Query query = em.createQuery(" from Grupo gr where gr.codGrupo = :codGrupo");
		query.setParameter("codGrupo", codGrupo);
		return (Grupo) query.getSingleResult();
	}
	
	
	}
	

