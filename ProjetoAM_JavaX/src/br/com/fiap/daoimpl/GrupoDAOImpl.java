package br.com.fiap.daoimpl;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.primefaces.model.DefaultStreamedContent;

import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.rc.ComentarioGrupoRC;

public class GrupoDAOImpl extends DAOImpl<Grupo, Integer> implements GrupoDAO {

	/**
	 * Construtor padrão
	 *
	 * @param entityManager Gerenciador das persistências
	 * @author Ariel Molina 
	 */
	public GrupoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	/**
	 * Busca os Grupos que a Pessoa participa
	 *
	 * @param pessoa Pessoa utilizada na busca
	 * @return Grupos encontrados
	 * @author Ariel Molina 
	 */
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
		}

		Collections.sort(grupos, new Comparator<Grupo>() {
			public int compare(Grupo object1, Grupo object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);

		return grupos;
	}

	/**
	 * Busca os Grupos cadastrados
	 *
	 * @return Grupos encontrados
	 * @author Graziele Vasconcelos 
	 */
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

		Collections.sort(grupos, new Comparator<Grupo>() {
			public int compare(Grupo object1, Grupo object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);
		return grupos;
	}

	/**
	 * Busca os Grupos da Pessoa
	 *
	 * @param codPessoa Código da Pessoa
	 * @return Grupos encontrados
	 * @author Graziele Vasconcelos 
	 */
	@Override
	public List<Grupo> consultaMeusGrupos(int codPessoa) {
		ArrayList<Integer> codigos  = new ArrayList<Integer>(); 
		TypedQuery<Grupo> query = (TypedQuery<Grupo>) em.createNativeQuery("select gru.* from am_grupo gru where gru.cod_grupo in (select cod_grupo from am_pessoa_grupo where cod_pessoa = ? and rownum <= 3)",Grupo.class);
		query.setParameter(1, codPessoa);
		List<Grupo> grupos = query.getResultList();

		for (Grupo grupo : grupos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo ");
			queryQtd.setParameter("codGrupo", grupo.getCodGrupo());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			grupo.setQuantidade(qtd);
		}
		return grupos;
	}

	/**
	 * Busca Pessoas para adicionar ao Grupo (pessoas que não estão no Grupo)
	 *
	 * @param codGrupo Grupo utilizado na busca
	 * @return Pessoas encontradas
	 * @author Ariel Molina
	 */
	@Override
	public List<Pessoa> buscarPessoasParaAdicionarAoGrupo(int codGrupo) {
		TypedQuery<Pessoa> query = (TypedQuery<Pessoa>) em.createNativeQuery("SELECT * FROM AM_PESSOA P where cod_pessoa not in" +
				"(select cod_pessoa from am_pessoa_grupo where cod_grupo = :codGrupo)",Pessoa.class);
		query.setParameter("codGrupo",codGrupo);
		return query.getResultList();

	}

	/**
	 * Busca os Grupos que a Pessoa participa
	 *
	 * @param pessoa Pessoa utilizada na busca
	 * @return Grupos encontrados
	 * @author Graziele Vasconcelos
	 */
	@Override
	public Grupo buscarInfoGrupo(int codGrupo) {
		Query query = em.createQuery(" from Grupo gr where gr.codGrupo = :codGrupo");
		query.setParameter("codGrupo", codGrupo);
		return (Grupo) query.getSingleResult();
	}

	/**
	 * Busca o número de membros de um Grupo
	 *
	 * @param codGrupo Código do Grupo a ser buscado
	 * @return Numero de membros
	 * @author Graziele Vasconcelos
	 */
	@Override
	public BigDecimal buscarNumeroMembros(int codGrupo) {
		//FAZER RESULTSET
		Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
		queryQtd.setParameter("codGrupo", codGrupo);
		BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
		return qtd;
	}

	/**
	 * Busca os Grupos visíveis ao usuário pelo nome (grupos abertos e grupos que o usuário participa)
	 * 
	 * @param pessoa Pessoa da sessão que será utilizado na busca
	 * @param nome Nome do Grupo
	 * @return Grupos encontrados
	 * @author Ariel Molina 
	 */
	@Override
	public List<Grupo> buscarGruposVisiveisPorNome(Pessoa pessoa, String nome){
		TypedQuery<Grupo> query = (TypedQuery<Grupo>) em.createNativeQuery("SELECT * FROM AM_GRUPO WHERE privacidade = :priv " +
				"OR cod_grupo in (SELECT cod_grupo " +
				"FROM AM_PESSOA_GRUPO WHERE cod_pessoa = :codPessoa) AND UPPER(nome_grupo) LIKE UPPER(:nome)",Grupo.class);
		query.setParameter("priv", Privacidade.Aberto.ordinal());
		query.setParameter("codPessoa", pessoa.getCodPessoa());
		query.setParameter("nome", "%"+nome+"%");
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

	/**
	 * Busca os Grupos que a Pessoa participa pelo nome
	 *
	 * @param pessoa Pessoa utilizada na busca
	 * @param nome Nome total ou parcial do Grupo utilizado na busca
	 * @return Grupos encontrados
	 * @author Ariel Molina 
	 */
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

	/**
	 * Busca os Grupos visíveis ao usuário (grupos abertos e grupos que o usuário participa)
	 * 
	 * @param pessoa Pessoa da sessão que será utilizado na busca
	 * @return Grupos encontrados
	 * @author Ariel Molina
	 */
	@Override
	public List<Grupo> buscarGruposVisiveis(Pessoa pessoa) {
		TypedQuery<Grupo> query = (TypedQuery<Grupo>) em.createNativeQuery("SELECT * FROM AM_GRUPO WHERE privacidade = :priv " +
				"OR cod_grupo in (SELECT cod_grupo " +
				"FROM AM_PESSOA_GRUPO WHERE cod_pessoa = :codPessoa)",Grupo.class);
		query.setParameter("priv", Privacidade.Aberto.ordinal());
		query.setParameter("codPessoa", pessoa.getCodPessoa());
		List<Grupo> grupos = query.getResultList();

		for (Grupo g: grupos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_grupo pg where pg.cod_grupo = :codGrupo");
			queryQtd.setParameter("codGrupo", g.getCodGrupo());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			g.setQuantidade(qtd);
		}

		Collections.sort(grupos, new Comparator<Grupo>() {
			public int compare(Grupo object1, Grupo object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);

		return grupos;
	}

	/**
	 * Busca os Membros do Grupo
	 *
	 * @param codGrupo Código do Grupo
	 * @return Membros encontrados do Grupo
	 * @author Ariel Molina/Graziele Vasconcelos
	 */
	@Override
	public List<Pessoa> buscarMembrosDoGrupo(int codGrupo) {
		@SuppressWarnings("unchecked")
		TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery
		("SELECT * FROM AM_PESSOA  WHERE cod_pessoa IN (SELECT cod_pessoa FROM AM_PESSOA_GRUPO WHERE cod_grupo = :codGrupo) " +
				"and cod_pessoa not in (select COD_PESSOA from am_moderador_grupo where COD_GRUPO = :codGrupo) " +
				"and cod_pessoa not in (select cod_adm from am_grupo where cod_grupo = :codGrupo)",
				Pessoa.class);

		p.setParameter("codGrupo", codGrupo);
		return p.getResultList();

	}

	/**
	 * Busca os Comentários do Grupo
	 *
	 * @param codGrupo Código do Grupo
	 * @return Comentários do Grupo
	 * @author Graziele Vasconcelos
	 */
	@Override
	public List<ComentarioGrupoRC> buscarComentariosPeloGrupo(int codGrupo) {
		List<ComentarioGrupoRC> commentsGrupos = new ArrayList<ComentarioGrupoRC>();
		String queryStr = "SELECT NEW br.com.fiap.rc.ComentarioGrupoRC (c.codComentario,p.codPessoa, p.apelido, p.imgPerfil, c.comentario, c.dataHora) " +
				"FROM ComentarioGrupo c JOIN c.codPessoa p " +
				"WHERE c.codGrupo.codGrupo = :cod";

		TypedQuery<ComentarioGrupoRC> query = em.createQuery(queryStr, ComentarioGrupoRC.class);
		query.setParameter("cod", codGrupo);

		commentsGrupos = query.getResultList();

		Collections.sort(commentsGrupos, new Comparator<ComentarioGrupoRC>() {
			public int compare(ComentarioGrupoRC object1, ComentarioGrupoRC object2) {
				return object2.getDataHora().compareTo(object1.getDataHora());
			}
		}
				);

		return commentsGrupos;
	}

	/**
	 * Busca os próximos Eventos do Grupo
	 *
	 * @param codGrupo Código do Grupo
	 * @return Próximos Eventos do Grupo
	 * @author Graziele Vasconcelos
	 */
	@Override
	public List<Evento> buscarProximosEventos(int codGrupo) {
		List<Evento> eventos = new ArrayList<Evento>();
		String queryStr = "SELECT eve.* FROM AM_EVENTO eve WHERE eve.cod_evento IN " + 
				"(SELECT eg.cod_evento FROM AM_EVENTO_GRUPO eg WHERE eg.cod_grupo = ? ) AND eve.data_evento >= ?";
		TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery(queryStr, Evento.class);
		query.setParameter(1, codGrupo);
		query.setParameter(2, Calendar.getInstance());

		eventos = query.getResultList();
		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return object1.getDtEvento().compareTo(object2.getDtEvento());
			}
		}
				);
		return eventos;
	}

	/**
	 * Busca os últimos Eventos do Grupo
	 *
	 * @param codGrupo Código do Grupo
	 * @return Últimos Eventos do Grupo
	 * @author Graziele Vasconcelos
	 */
	@Override
	public List<Evento> buscarHistoricoEvento(int codGrupo) {
		List<Evento> eventos = new ArrayList<Evento>();
		String queryStr = "SELECT eve.* FROM AM_EVENTO eve WHERE eve.cod_evento IN " + 
				"(SELECT eg.cod_evento FROM AM_EVENTO_GRUPO eg WHERE eg.cod_grupo = ? ) AND eve.data_evento < ?";
		TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery(queryStr, Evento.class);
		query.setParameter(1, codGrupo);
		query.setParameter(2, Calendar.getInstance());

		eventos = query.getResultList();
		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return object1.getDtEvento().compareTo(object2.getDtEvento());
			}
		}
				);
		return eventos;
	}

	/**
	 * Busca seis membros do Grupo
	 *
	 * @param codGrupo Código do Grupo ser procurada
	 * @return Membros encontrados
	 * @author Johnatta Santos/Graziele Vasconcelos
	 */
	@Override
	public List<Pessoa> buscarMembrosDoGrupoRow(int codGrupo) {
		@SuppressWarnings("unchecked")
		TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery
		("SELECT * FROM AM_PESSOA  WHERE cod_pessoa IN (SELECT cod_pessoa FROM AM_PESSOA_GRUPO WHERE cod_grupo = :codGrupo) " +
				"and cod_pessoa not in (select COD_MODERADOR_PESSOA from am_moderador_grupo where COD_MODERADOR_GRUPO = :codGrupo) " +
				"and cod_pessoa not in (select cod_adm from am_grupo where cod_grupo = :codGrupo) and rownum <= 6", Pessoa.class);
		p.setParameter("codGrupo", codGrupo);
		return p.getResultList();
	}
	
	
	/**
	 * Busca os membros do grupo incluindo moderador
	 *
	 * @param codGrupo Código do Grupo ser procurada
	 * @return Membros encontrados
	 * @author Graziele Vasconcelos
	 */
	@Override
	public List<Pessoa> buscarMembrosDoGrupoComModerador(int codGrupo) {
		TypedQuery <Pessoa> p = (TypedQuery<Pessoa>) em.createNativeQuery
		("SELECT * FROM AM_PESSOA  WHERE cod_pessoa IN (SELECT cod_pessoa FROM AM_PESSOA_GRUPO WHERE cod_grupo = :codGrupo) " +
				"and cod_pessoa not in (select cod_adm from am_grupo where cod_grupo = :codGrupo)", Pessoa.class);
		p.setParameter("codGrupo", codGrupo);
		return p.getResultList();
	}

}