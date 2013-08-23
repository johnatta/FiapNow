 package br.com.fiap.teste;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.Pessoa;

public class TesteJohnatta {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		Pessoa pessoa = new Pessoa();
		ModeradorGrupo mg = new ModeradorGrupo();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");




		/* Informaçoes do Grupo
				Grupo gr = new Grupo();
				GrupoDAO grDAO = new GrupoDAOImpl(em);

				gr = grDAO.buscaInfoGrupo(1);

				System.out.println("Nome do Grupo: " + gr.getNomeGrupo());
				System.out.println("descricao: " + gr.getDescricao());
				System.out.println("Privacidade do grupo: " + gr.getPrivacidade());
		 */

	
		
			//   Buscando Membros do grupo 
			  
			PessoaDAO pessoaDAO = new PessoaDAOImpl(em);

				
				List<Pessoa> pessoa1 = pessoaDAO.buscarMembrosDoGrupo(1);  
				
				for(Pessoa resultado : pessoa1){
					System.out.println();
					System.out.println("Membro do grupo:" + resultado.getNome());
				
				}
		
				// buscarModeradoresDoGrupo		
				
				ModeradorGrupoDAO mgDAO = new ModeradorGrupoDAOImpl(em);
				
				List<Pessoa> pessoa2 = mgDAO.buscarModeradoresDoGrupo(3);
				
				for(Pessoa resultado : pessoa2){
					System.out.println();				
					System.out.println("Moderador do grupo:" + resultado.getNome());
				
		        }  
				
					
				

		}
}




