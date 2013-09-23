package br.com.fiap.daoimpl;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.primefaces.model.DefaultStreamedContent;

import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.rc.ComentarioGrupoRC;

public class GrupoDAOImpl extends DAOImpl<Grupo, Integer> implements GrupoDAO {

	public GrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Grupo> buscaGruposDaPessoa(Pessoa pessoa) {
		TypedQuery<Grupo> q = (TypedQuery<Grupo>) em.createNativeQuery("select * from am_grupo gru where gru.cod_grupo in (select pg.cod_grupo from am_pessoa_grupo pg where pg.cod_pessoa = ?)", Grupo.class);
		q.setParameter(1, pessoa.getCodPessoa());
		List<Grupo> grupos = q.getResultList();

		for (Grupo grupo : grupos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
			queryQtd.setParameter("codGrupo", grupo.getCodGrupo());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			grupo.setQuantidade(qtd);

			grupo.setFoto(new DefaultStreamedContent(new ByteArrayInputStream(grupo.getImgGrupo()), "image/jpg"));
		}
		
		Collections.sort(grupos, new Comparator<Grupo>() {
			public int compare(Grupo object1, Grupo object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);
		
		return grupos;
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

			g.setFoto(new DefaultStreamedContent(new ByteArrayInputStream(g.getImgGrupo()), "image/jpg"));
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

	@Override
	public BigDecimal buscarNumeroMembros(int codGrupo) {
		//FAZER RESULTSET
		Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
		queryQtd.setParameter("codGrupo", codGrupo);
		BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
		return qtd;
	}

	@Override
	public List<Grupo> buscarGruposAbertosPorNome(String nome){
		TypedQuery<Grupo> query = em.createQuery("from Grupo gru where upper(gru.nomeGrupo) like upper(:nome) and privacidade = :priv",Grupo.class);
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("priv", Privacidade.Aberto);
		List<Grupo> grupos = query.getResultList();

		for (Grupo grupo : grupos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
			queryQtd.setParameter("codGrupo", grupo.getCodGrupo());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			grupo.setQuantidade(qtd);
		}
		
		Collections.sort(grupos, new Comparator<Grupo>() {
			public int compare(Grupo object1, Grupo object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);
		
		return grupos;
	}

	@Override
	public List<Grupo> buscarMeusGruposPorNome(Pessoa pessoa, String nome){
		TypedQuery<Grupo> query = (TypedQuery<Grupo>) em.createNativeQuery("select gru.* from am_grupo gru where gru.cod_grupo in (select cod_grupo from am_pessoa_grupo where cod_pessoa = ?)" +
				" and upper(gru.nome_grupo) like upper(?)",Grupo.class);
		query.setParameter(1, pessoa.getCodPessoa());
		query.setParameter(2, "%"+nome+"%");
		List<Grupo> grupos = query.getResultList();

		for (Grupo grupo : grupos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
			queryQtd.setParameter("codGrupo", grupo.getCodGrupo());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			grupo.setQuantidade(qtd);
		}
		
		Collections.sort(grupos, new Comparator<Grupo>() {
			public int compare(Grupo object1, Grupo object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);
		
		return grupos;
	}
	@Override
	public Grupo buscarGrupoCadastrado(int codAdm) { 
		TypedQuery<Grupo> query = (TypedQuery<Grupo>) em.createNativeQuery("Select g.* from AM_Grupo g where g.cod_adm = ? and rownum = 1 order by g.cod_grupo desc");
		query.setParameter(1, codAdm);
		return query.getSingleResult();
	}

	@Override
	public List<Grupo> buscarGruposAbertos() {
		TypedQuery<Grupo> query = em.createQuery("from Grupo where privacidade = :priv",Grupo.class);
		query.setParameter("priv", Privacidade.Aberto);
		List<Grupo> grupos = query.getResultList();

		for (Grupo g: grupos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
			queryQtd.setParameter("codGrupo", g.getCodGrupo());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			g.setQuantidade(qtd);

			g.setFoto(new DefaultStreamedContent(new ByteArrayInputStream(g.getImgGrupo()), "image/jpg"));
		}
		
		Collections.sort(grupos, new Comparator<Grupo>() {
			public int compare(Grupo object1, Grupo object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);

		return grupos;
	}

	@Override
	public List<Pessoa> buscarMembrosDoGrupo(int codGrupo) {
		@SuppressWarnings("unchecked")
		TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery("SELECT * FROM AM_PESSOA WHERE cod_pessoa IN (SELECT cod_pessoa  FROM AM_PESSOA_GRUPO  WHERE cod_grupo = :codGrupo)", Pessoa.class);
		p.setParameter("codGrupo", codGrupo);
		return p.getResultList();

	}


	@Override
	@OrderBy("c.dataHora desc")
	public List<ComentarioGrupoRC> buscarComentariosPeloGrupo(int codGrupo) {
		String queryStr = "SELECT NEW br.com.fiap.rc.ComentarioGrupoRC (p.codPessoa, p.apelido, p.imgPerfil, c.comentario, c.dataHora) " +
			      "FROM ComentarioGrupo c JOIN c.codPessoa p " +
			      "WHERE c.codGrupo.codGrupo = :cod and rownum <= 10";
		
		TypedQuery<ComentarioGrupoRC> query = em.createQuery(queryStr, ComentarioGrupoRC.class);
		query.setParameter("cod", codGrupo);
		
		return query.getResultList();
	}

}


