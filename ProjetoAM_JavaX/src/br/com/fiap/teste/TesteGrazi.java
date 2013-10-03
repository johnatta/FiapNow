package br.com.fiap.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.rc.ComentarioGrupoRC;

public class TesteGrazi {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		/*
		Pessoa pes = new Pessoa();
		PessoaDAO pesDAO = new PessoaDAOImpl(em);

		pes = pesDAO.buscarInformacoes(2);

		System.out.println("Nome: " + pes.getNome());
		System.out.println("Cidade: " + pes.getCodEndereco().getCidade());
		System.out.println("Tel: " + pes.getTelRes());
		 */
		List<Grupo> grupos = new ArrayList<Grupo>();
		Grupo g = new Grupo();
		List<Esporte> esportes = new ArrayList<Esporte>();
		Esporte e = new Esporte();
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		BigDecimal numMembros;
		PessoaDAO pDAO = new PessoaDAOImpl(em);
		Pessoa p = new Pessoa();
		PedidoGrupoDAO pgDAO = new PedidoGrupoDAOImpl(em);
		PedidoGrupo pg = new PedidoGrupo();
		ModeradorGrupo modGrupo = new ModeradorGrupo();
		ModeradorGrupoDAO modDAO = new ModeradorGrupoDAOImpl(em);
		List<Pessoa> moderadores = modDAO.buscarModeradoresDoGrupoRowNum(6);
		GrupoDAO gDAO = new GrupoDAOImpl(em); 
		List<ComentarioGrupoRC> comentarios = new ArrayList<ComentarioGrupoRC>();
		List<Evento> eventos ;
		ComentarioGrupo comentarioGrupo = new ComentarioGrupo();
		ComentarioGrupoDAO comentarioGrupoDAO = new ComentarioGrupoDAOImpl(em);

		/*
		for (Pessoa mod : moderadores) {
			//System.err.println(mod.getNome());
		}

		comentarios = gDAO.buscarComentariosPeloGrupo(4);
		System.out.println(comentarios);

		for(ComentarioGrupoRC comentarioGrupo : comentarios){
			System.err.println("APELIDO: " + comentarioGrupo.getApelido());
			System.err.println("COMENTARIO: " + comentarioGrupo.getComentario());
			System.err.println("HORA: " + comentarioGrupo.getDataHora());

			//Calendar dataC = comentarioGrupo.getDataHora().getInstance();
			//Date data = dataC.getTime();
			//Date data = comentarioGrupo.getDataHora().getInstance().getTime();
			//System.out.println(data.getTime());

			Date data = comentarioGrupo.getDataHora().getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
			System.out.println(sdf.format(data));
			//String dataFormatada = sdf.format(data);
			//System.out.println(dataFormatada);
			/*
		 * Calendar c = Calendar.getInstance(); Date data = c.getTime(); DateFormat f = DateFormat.getDateInstance(); Date data2 = f.parse("12/01/1995"); System.out.println(data2); SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); System.out.println("Data formatada: "+sdf.format(data)); //Converte Objetos System.out.println(�Data convertida: �+sdf.parse("02/08/1970")); }

Leia mais em: Trabalhando com as classes Date, Calendar e SimpleDateFormat em Java http://www.devmedia.com.br/trabalhando-com-as-classes-date-calendar-e-simpledateformat-em-java/27401#ixzz2fgvoEtov
		 * 
		 * 
		 * 
		Calendar c = Calendar.getInstance();
		Date data = c.getTime(); 
		DateFormat f = DateFormat.getDateInstance(); 
		Date data2 = f.parse("12/01/1995"); 
		System.out.println(data2); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		System.out.println("Data formatada: "+sdf.format(data));
		//Converte Objetos System.out.println(�Data convertida: �+sdf.parse("02/08/1970")); }
		}

		System.err.println("---------------------------------------------------------------------");

		g = gDAO.buscarInfoGrupo(2);
		System.out.println("Nome: " + g.getNomeGrupo() );
		System.out.println("Esporte: " + g.getEsportes().set(1, e).getNome());
		System.out.println("Privacidade: " + g.getPrivacidade());
		System.out.println("Quantidade: " + g.getQuantidade());

		System.err.println("---------------------------------------------------------------------");

		p = pDAO.buscarInformacoes(1);

		grupos = gDAO.buscaGruposDaPessoa(p);
		for (Grupo grupo : grupos) {
			System.err.println("CODIGO GRUPO " + grupo.getCodGrupo());
			System.out.println("DESCRICAO " + grupo.getDescricao());
			System.out.println("NOME " + grupo.getNomeGrupo());
			for(Esporte esp : grupo.getEsportes() ){
			System.out.println("ESPORTE " + esp.getNome());
			}
			System.out.println("PRIVACIDADE " + grupo.getPrivacidade());
			System.out.println("IMAGEM " + grupo.getImgGrupo());
			System.out.println();
			System.err.println("---------------------------------------------------------------------");
		}
		 */
		/*

		grupos = gDAO.buscaGruposPorUsuario(2);
		for (Grupo grupo : grupos) {
			System.out.println("CODIGO GRUPO " + grupo.getCodGrupo());
			System.out.println("DESCRICAO " + grupo.getDescricao());
			System.out.println("IMAGEM " + grupo.getImgGrupo());
			System.out.println("NOME " + grupo.getNomeGrupo());
			System.out.println("PRIVACIDADE " + grupo.getPrivacidade());
			System.out.println("Quantidade " + grupo.getQuantidade());
			System.out.println();
		}

		esportes = espDAO.buscarTodosEsportes();
		for (Esporte esporte : esportes) {
			System.out.println(esporte.getCodEsporte());
			System.out.println(esporte.getNome());
		}
		g = gDAO.buscarGrupoCadastrado(2);
		System.out.println("NOME : " + g.getNomeGrupo());
		System.out.println("CODIGO : " + g.getCodGrupo());


		//numMembros = gDAO.buscarNumeroMembros(56);
		//System.out.println(numMembros);
		//e = espDAO.buscarPorNome("corrida");
		//System.out.println(e.getNome());


		grupos = gDAO.buscarGrupos();

		for (Grupo grupoA : grupos) {
			System.out.println(grupoA.getDescricao());
		}

		grupos = gDAO.consultaMeusGrupos(1);

		for (Grupo grupoA : grupos) {
			System.out.println(grupoA.getDescricao());
		}
		
		
		comentarios = gDAO.buscarComentariosPorPessoa(3);
		
		for(ComentarioGrupoRC cg : comentarios){
			System.out.println(cg.getComentario());
			System.err.println("----------------------------");
		}
		
		
		List<Pessoa> pessoas = gDAO.buscarMembrosDoGrupo(1);
		
		for(Pessoa pesAB : pessoas){
			System.out.println("NOME" + pesAB.getNome());
		}
		
		grupos = gDAO.consultaMeusGrupos(1);

		for (Grupo grupoA : grupos) {
			System.out.println(grupoA.getDescricao());
		}
		eventos = gDAO.buscarProximosEventos(68);
		
		for(Evento eve : eventos){
			System.out.println("DESCRICAO" + eve.getDescricao());
			System.err.println("---------------------------");
			
		}
		
		eventos = gDAO.buscarHistoricoEvento(2);
		
		for(Evento eve : eventos){
			System.out.println("DESCRICAO" + eve.getDescricao());
			System.err.println("---------------------------");
			
		}
		comentarioGrupo = comentarioGrupoDAO.searchByID(28);
		comentarioGrupoDAO.remove(comentarioGrupo);		

		modGrupo = modDAO.buscarModeradorGrupo(1, 3);
		
		System.out.println("NOME GRUPO: " + modGrupo.getGrupo().getNomeGrupo());
		System.out.println("NOME PESSOA: " + modGrupo.getPessoa().getNome());
		Calendar dataProx = Calendar.getInstance();
		dataProx.set(2014, 10, 22);
		Calendar dataHist = Calendar.getInstance();
		dataHist.set(2013, 06, 20);
		
		p = pDAO.searchByID(1);
		
		g = gDAO.searchByID(6);
		grupos.add(g);
		
		EventoDAO eDAO = new EventoDAOImpl(em);
		Evento evento = new Evento();
		evento.setNome("Skate SP-BR");
		evento.setDescricao("Skate urbano em SP.");
		evento.setPrivacidade(Privacidade.Aberto);
		evento.setCusto(00);
		evento.setTelContato("01120500059");
		evento.setImgEvento(new byte [4]);
		evento.setDtEvento(dataProx);
		evento.setAdm(p);
		evento.setGrupos(grupos);
		//eDAO.insert(evento);
		
		Evento eventoA = new Evento();
		eventoA.setNome("Skate RS-BR");
		eventoA.setDescricao("Skate urbano em RS.");
		eventoA.setPrivacidade(Privacidade.Aberto);
		eventoA.setCusto(00);
		eventoA.setTelContato("05150931883");
		eventoA.setImgEvento(new byte [4]);
		eventoA.setDtEvento(dataHist);
		eventoA.setAdm(p);
		eventoA.setGrupos(grupos);
		//eDAO.insert(eventoA);
		 */
		g = gDAO.searchByID(6);
		p = pDAO.searchByID(1);

		MensagemGrupoDAO mDAO = new MensagemGrupoDAOImpl(em);
		MensagemGrupo mg = new MensagemGrupo();
		mg.setConfirmacao(Confirmacao.NAO);
		mg.setTitulo("Evento novo");
		mg.setDescricao("Em breve not�cias do pr�ximo evento que o grupo vai participar");
		mg.setGrupo(g);
		mg.setPessoa(p);
		//mDAO.insert(mg);
		
		MensagemGrupo mgA = new MensagemGrupo();
		mgA.setConfirmacao(Confirmacao.NAO);
		mgA.setTitulo("Patrocinadores");
		mgA.setDescricao("Minist�rio do Esporte encaminha o recibo � Receita Federal, que abate o valor repassado do Imposto");
		mgA.setGrupo(g);
		mgA.setPessoa(p);
		mDAO.insert(mgA);
		
		MensagemGrupo mgB = new MensagemGrupo();
		mgB.setConfirmacao(Confirmacao.NAO);
		mgB.setTitulo("Lei do Incentivo");
		mgB.setDescricao("Promover a inclus�o social e desportiva e despertar o interesse pelo ideal paraol�mpico s�o os");
		mgB.setGrupo(g);
		mgB.setPessoa(p);
		mDAO.insert(mgB);
		
		
		
	}

}
