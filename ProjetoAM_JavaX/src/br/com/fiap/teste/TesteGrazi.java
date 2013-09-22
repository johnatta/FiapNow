package br.com.fiap.teste;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
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
