package br.com.fiap.testeInserts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.entity.Evento;

public class TesteAriel {
	
	public static void main(String[] args) {
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		//Teste do m�todo buscarMembrosPorEvento(codEvento)
		/*PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		List<Pessoa> pessoas = pessoaDAO.buscarMembrosPorEvento(1);
		for(Pessoa pes : pessoas){
			System.out.println("C�digo: " + pes.getCodPessoa());
			System.out.println("Nome: " + pes.getNome());
			System.out.println("Apelido: " + pes.getApelido());
		}
		
		if(pessoas.size() == 0) {
			System.out.println("Nenhum resultado encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//Teste do m�todo buscarPeloCodigo(int codEvento)
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		Evento evento = eventoDAO.buscarPeloCodigo(1);
		System.out.println("C�digo do Evento: " + evento.getCodEvento());
		System.out.println("Descri��o do Evento: " + evento.getDescricao());
		System.out.println("Custo: " + evento.getCusto());
		System.out.println("Data do Evento: " + sdf.format(evento.getDtEvento().getTime()));*/
		
		/*---------------------------------------------------*/
		
		//Teste do m�todo buscarComentariosPeloEvento(int codEvento)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<ComentarioEvento> comentariosEvento = eventoDAO.buscarComentariosPeloEvento(1);
		for(ComentarioEvento comentario : comentariosEvento){
			System.out.println("C�digo do coment�rio: " + comentario.getCodComentario());
			System.out.println("C�digo da pessoa: " + comentario.getCodPessoa());
			System.out.println("C�digo do evento: " + comentario.getCodEvento());
			System.out.println("Coment�rio: " + comentario.getComentario());
		}
		if(comentariosEvento.size() == 0){
			System.out.println("Nenhum coment�rio encontrado para o evento!");
		}*/
		
		//Teste do m�todo buscarMembrosPorEvento(codEvento)
		/*PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		List<Pessoa> pessoas = pessoaDAO.buscarModeradoresDoEvento(1);
		for(Pessoa pes : pessoas){
			System.out.println("C�digo: " + pes.getCodPessoa());
			System.out.println("Nome: " + pes.getNome());
			System.out.println("Apelido: " + pes.getApelido());
		}
		
		if(pessoas.size() == 0) {
			System.out.println("Nenhum resultado encontrado!");
		}*/
		
	}
	
}
