package br.com.fiap.teste;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import oracle.jdbc.internal.ObjectData;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
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
		Grupo grupo = new Grupo();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/*	// Informaçoes do Grupo					
				GrupoDAO grupoDAO = new GrupoDAOImpl(em);
				grupo = grupoDAO.buscarInfoGrupo(1);
				System.out.println("-------Informaçoes do grupo-------");
				System.out.println();
				System.out.println("Cod Do Grupo " + grupo.getCodGrupo());
				System.out.println("Nome do Grupo: " + grupo.getNomeGrupo());
				System.out.println("Descricao do Grupo: " + grupo.getDescricao());
				System.out.println("Privacidade do grupo: " + grupo.getPrivacidade());
	 */
	
	/*	//   Buscando Membros do grupo 

		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		List<Pessoa> pessoa1 = pessoaDAO.buscarMembrosDoGrupo(1);  
		
		System.out.println("---------Buscando Membros do Grupo: ----------");
		for(Pessoa resultado : pessoa1){
			System.out.println();
			System.out.println("Membro do grupo:" + resultado.getNome());
			System.out.println("Sobrenome: " + resultado.getSobrenome());

		} */

	/*	// buscarModeradoresDoGrupo		

			ModeradorGrupoDAO mgDAO = new ModeradorGrupoDAOImpl(em);

			List<Pessoa> pessoa2 = mgDAO.buscarModeradoresDoGrupo(1);
			System.out.println("----Moderadores do Grupo------");

				for(Pessoa resultado : pessoa2){
					System.out.println();				
					System.out.println("Moderador do grupo:" + resultado.getNome());

		} */  			
		//	Proximos eventos do grupo:
		/*	EventoDAO eventoDAO = new EventoDAOImpl(em);
				System.out.println("-------Data do proximo evento-------");
			List<Evento> evento = eventoDAO.buscarProximosEventos(2);
				for(Evento resultado : evento){
					System.out.println();
					System.out.println("Data do proximo Evento" + resultado.getDtEvento());
		*/	
			//Historico de evento do grupo:
				
					/*EventoDAO eventoDAO = new EventoDAOImpl(em);
	
					List<Evento> evento1 = eventoDAO.historicoDeEventos(2);
								
							System.out.println();
						for(Evento resultado1 : evento1){
							
							System.out.println("Historico de eventos antigo: " + sdf.format(resultado1.getDtEvento().getTime()));
						}*/
			
		}






	}












