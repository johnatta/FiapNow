package br.com.fiap.daoimpl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.dao.EventoDAO;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.rc.ComentarioEventoRC;

public class EventoDAOImpl extends DAOImpl<Evento, Integer> implements EventoDAO{

	public EventoDAOImpl(EntityManager entityManager) {
		super(entityManager);
	}

	/**
	 * Busca um Evento pelo código
	 *
	 * @param codEvento Código do Evento
	 * @return Evento encontrado
	 * @author Ariel Molina 
	 */
	@Override
	public Evento buscarPeloCodigo(int codEvento) {
		TypedQuery<Evento> query = em.createQuery("from Evento where codEvento = :cod", Evento.class);
		query.setParameter("cod", codEvento);
		return query.getSingleResult();
	}

	/**
	 * Busca os Comentários de um Evento
	 *
	 * @param codEvento Código do Evento
	 * @return Comentários do Evento
	 * @author Ariel Molina 
	 */
	@Override
	public List<ComentarioEventoRC> buscarComentariosPeloEvento(int codEvento) {
		String queryStr = "SELECT NEW br.com.fiap.rc.ComentarioEventoRC(p.codPessoa, p.apelido, p.imgPerfil, c.comentario, c.dtHora) " +
				"FROM ComentarioEvento c JOIN c.pessoa p " +
				"WHERE c.evento.codEvento = :cod and rownum <= 10";
		TypedQuery<ComentarioEventoRC> query = em.createQuery(queryStr, ComentarioEventoRC.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

	/**
	 * Busca os Membros do Evento
	 *
	 * @param codEvento Código do Evento
	 * @return Membros do Evento
	 * @author Ariel Molina 
	 */
	@Override
	public List<Pessoa> buscarMembrosPorEvento(int codEvento) {
		TypedQuery<Pessoa> query = (TypedQuery<Pessoa>) em.createNativeQuery("SELECT * FROM AM_PESSOA WHERE cod_pessoa IN " +
				"(SELECT cod_pessoa FROM AM_PESSOA_EVENTO WHERE COD_EVENTO = :cod)",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

	/**
	 * Busca os Moderadores do Evento
	 *
	 * @param codEvento Código do Evento
	 * @return Moderadores do Evento
	 * @author Ariel Molina 
	 */
	@Override
	public List<Pessoa> buscarModeradoresDoEvento(int codEvento) {
		TypedQuery<Pessoa> query = (TypedQuery<Pessoa>) em.createNativeQuery("SELECT * FROM AM_PESSOA WHERE COD_PESSOA IN " +
				"(SELECT COD_PESSOA FROM AM_MODERADOR_EVENTO WHERE COD_EVENTO = :cod)",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

	/**
	 * Busca as Pessoas que não estão no Evento
	 *
	 * @param codEvento Código do Evento
	 * @return Pessoas encontradas
	 * @author Ariel Molina/Felipe Mauro 
	 */
	@Override
	public List<Pessoa> buscarPessoasForaEvento(int codEvento) {
		TypedQuery<Pessoa> query = em.createQuery("select PES.codPessoa, PES.apelido, PES.imgPerfil " +
				"from Pessoa PES where PES.codPessoa not in" +
				"( select codPessoa from Pessoa p inner join p.eventos e where e.codEvento= :cod)",Pessoa.class);
		query.setParameter("cod", codEvento);
		return query.getResultList();
	}

	/**
	 * Busca os Eventos cadastrados
	 *
	 * @return Eventos encontrados
	 * @author Ariel Molina 
	 */
	@Override
	public List<Evento> buscarEventos() {
		TypedQuery<Evento> query = em.createQuery("from Evento",Evento.class);
		List<Evento> eventos = query.getResultList();
		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}
		return eventos;
	}

	/**
	 * Busca os Eventos para a Pessoa
	 *
	 * @param pessoa Pessoa que será utilizada na busca
	 * @return Eventos da Pessoa
	 * @author Ariel Molina 
	 */
	@Override
	public List<Evento> buscarEventosDaPessoa(Pessoa pessoa) {
		TypedQuery<Evento> q = (TypedQuery<Evento>) em.createNativeQuery("select * from am_evento eve where eve.cod_evento in (select pe.cod_evento from am_pessoa_evento pe where pe.cod_pessoa = ?) and eve.data_evento >= ?", Evento.class);
		q.setParameter(1, pessoa.getCodPessoa());
		q.setParameter(2, Calendar.getInstance());
		List<Evento> eventos = q.getResultList();

		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}

		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);

		return eventos;
	}

	/**
	 * Busca os Eventos visíveis ao usuário pelo nome (eventos abertos e eventos que o usuário participa) a partir da data atual
	 * 
	 * @param pessoa Pessoa da sessão que será utilizado na busca
	 * @param nome Nome completo ou parcial do Evento
	 * @return Eventos abertos encontrados
	 * @author Ariel Molina 
	 */
	@Override
	public List<Evento> buscarEventosVisiveisPorNome(Pessoa pessoa,String nome) {
		TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery("SELECT * FROM AM_EVENTO WHERE (privacidade = :priv " +
				"OR cod_evento in (SELECT cod_evento " +
				"FROM AM_PESSOA_EVENTO WHERE cod_pessoa = :codPessoa)) AND data_evento >= :today AND UPPER(nome) LIKE UPPER(:nome)",Evento.class);
		query.setParameter("codPessoa", pessoa.getCodPessoa());
		query.setParameter("priv", Privacidade.Aberto.ordinal());
		query.setParameter("today", Calendar.getInstance());
		query.setParameter("nome", "%"+nome+"%");
		List<Evento> eventos = query.getResultList();

		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}

		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);

		return eventos;
	}

	/**
	 * Busca os Eventos da Pessoa pelo Nome
	 *
	 * @param pessoa Pessoa que será utilizada na busca
	 * @param nome Nome completo ou parcial do Evento
	 * @return Eventos encontrados na busca
	 * @author Ariel Molina
	 */
	@Override
	public List<Evento> buscarMeusEventosPorNome(Pessoa pessoa, String nome) {
		TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery("select * from am_evento eve where eve.cod_evento in (select pe.cod_evento from am_pessoa_evento pe where pe.cod_pessoa = :codPessoa) and eve.data_evento >= :dt" +
				" and UPPER(eve.nome) like UPPER(:nome)", Evento.class);
		query.setParameter("codPessoa", pessoa.getCodPessoa());
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("dt", Calendar.getInstance());

		List<Evento> eventos = query.getResultList();

		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}

		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);

		return eventos;
	}

	/**
	 * Busca os Eventos visíveis ao usuário (eventos abertos e eventos que o usuário participa) a partir da data atual
	 * 
	 * @param pessoa Pessoa da sessão que será utilizado na busca
	 * @return Eventos abertos encontrados
	 * @author Ariel Molina 
	 */
	@Override
	public List<Evento> buscarEventosVisiveis(Pessoa pessoa) {
		TypedQuery<Evento> query = (TypedQuery<Evento>) em.createNativeQuery("SELECT * FROM AM_EVENTO WHERE (privacidade = :priv " +
				"OR cod_evento in (SELECT cod_evento " +
				"FROM AM_PESSOA_EVENTO WHERE cod_pessoa = :codPessoa)) AND data_evento >= :today ",Evento.class);
		query.setParameter("priv", Privacidade.Aberto.ordinal());
		query.setParameter("codPessoa", pessoa.getCodPessoa());
		query.setParameter("today", Calendar.getInstance());
		List<Evento> eventos = query.getResultList();

		for (Evento eve: eventos) {
			Query queryQtd = em.createNativeQuery("select count(*) from am_pessoa_evento pe where pe.cod_evento = :codEvento");
			queryQtd.setParameter("codEvento", eve.getCodEvento());
			BigDecimal qtd = (BigDecimal) queryQtd.getSingleResult();
			eve.setQuantidade(qtd);
		}

		Collections.sort(eventos, new Comparator<Evento>() {
			public int compare(Evento object1, Evento object2) {
				return Integer.compare(object2.getQuantidade().intValue(), object1.getQuantidade().intValue());
			}
		}
				);

		return eventos;
	}

}