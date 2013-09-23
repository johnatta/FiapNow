package br.com.fiap.teste;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;
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
		
		for (Pessoa mod : moderadores) {
			//System.err.println(mod.getNome());
		}
		
		GrupoDAO gDAO = new GrupoDAOImpl(em); 
		List<ComentarioGrupoRC> comentarios = gDAO.buscarComentariosPeloGrupo(4);
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
			 * Calendar c = Calendar.getInstance(); Date data = c.getTime(); DateFormat f = DateFormat.getDateInstance(); Date data2 = f.parse("12/01/1995"); System.out.println(data2); SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); System.out.println("Data formatada: "+sdf.format(data)); //Converte Objetos System.out.println(“Data convertida: ”+sdf.parse("02/08/1970")); }

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
		//Converte Objetos System.out.println(“Data convertida: ”+sdf.parse("02/08/1970")); }
*/
		}
		
		
		
		/*
		g = gDAO.buscaInfoBasicas(2);
		System.out.println("Nome: " + g.getNomeGrupo() );
		System.out.println("Esporte: " + g.getEsportes().set(1, e).getNome());
		System.out.println("Privacidade: " + g.getPrivacidade());
		System.out.println("Quantidade: " + g.getQuantidade());

		grupos = gDAO.buscaGruposDoUsuario(2);
		for (Grupo grupo : grupos) {
			System.out.println("CODIGO GRUPO " + grupo.getCodGrupo());
			System.out.println("DESCRICAO " + grupo.getDescricao());
			System.out.println("NOME " + grupo.getNomeGrupo());
			for(Esporte esp : grupo.getEsportes() ){
			System.out.println("ESPORTE " + esp.getNome());
			}
			System.out.println("PRIVACIDADE " + grupo.getPrivacidade());
			System.out.println("IMAGEM " + grupo.getImgGrupo());
			System.out.println();
		}
		
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
		 */
		
		
		//numMembros = gDAO.buscarNumeroMembros(56);
		//System.out.println(numMembros);
		//e = espDAO.buscarPorNome("corrida");
		//System.out.println(e.getNome());
		
		
	}

}
