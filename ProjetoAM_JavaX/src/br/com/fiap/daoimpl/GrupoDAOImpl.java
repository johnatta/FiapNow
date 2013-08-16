package br.com.fiap.daoimpl;

import java.math.BigDecimal;
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
		TypedQuery<Grupo> query = em.createQuery("select codgrupo, nomeGrupo from Grupo g where codGrupo in (select cod_grupo from am_pessoa_grupo pg where pg.cod_pessoa = :codPessoa)",Grupo.class);
		query.setFirstResult(0);
		query.setMaxResults(3); 
		query.setParameter("codPessoa",codPessoa);
		return query.getResultList();
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
		
		//Query query = em.createNativeQuery("select gru.cod_grupo, gru.nome_grupo, gru.imagem_grupo, (select count(*) from am_pessoa_grupo pg where pg.cod_grupo = ?) as nummembros from am_grupo gru order by nummembros", Grupo.class);
		//Query query = em.createQuery("select gru.cod_grupo, gru.nome_grupo, gru.img_grupo, (select count(pg) from am_pessoa_grupo pg where pg.cod_grupo = :cod_grupo) as nummembros from Grupo gru order by nummembros");
		//query.setParameter(1, codGrupo);
		//query.setParameter("codGrupo", codGrupo);
		return grupo;
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
