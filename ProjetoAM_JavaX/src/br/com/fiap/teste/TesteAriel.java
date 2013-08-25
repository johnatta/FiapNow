package br.com.fiap.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Pessoa;

public class TesteAriel {
	
	public static void main(String[] args) {
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		//TELA ADICIONAR MEMBRO GRUPO
		//Teste do método buscaUsuariosParaAdicionarAoGrupo(codGrupo)
		/*GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		List<Pessoa> pessoas = grupoDAO.buscarUsuariosParaAdicionarAoGrupo(1);
		for(Pessoa pes : pessoas){
			System.out.println("Código da pessoa que não está neste evento: " + pes.getCodPessoa());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA EVENTO
		//Informações do Evento
		//Teste do método buscarPeloCodigo(int codEvento)
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		Evento evento = eventoDAO.buscarPeloCodigo(1);
		System.out.println("Código do Evento: " + evento.getCodEvento());
		System.out.println("Descrição do Evento: " + evento.getDescricao());
		System.out.println("Custo: " + evento.getCusto());
		System.out.println("Data do Evento: " + sdf.format(evento.getDtEvento().getTime()));*/
		
		/*---------------------------------------------------*/
		
		//Membros
		//Teste do método buscarMembrosPorEvento(codEvento)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Pessoa> pessoas = eventoDAO.buscarMembrosPorEvento(1);
		for(Pessoa pes : pessoas){
			System.out.println("Código: " + pes.getCodPessoa());
			System.out.println("Nome: " + pes.getNome());
			System.out.println("Apelido: " + pes.getApelido());
		}
		
		if(pessoas.size() == 0) {
			System.out.println("Nenhum resultado encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//Comentários
		//Teste do método buscarComentariosPeloEvento(int codEvento)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<ComentarioEventoRC> comentariosEvento = eventoDAO.buscarComentariosPeloEvento(2);
		for(ComentarioEventoRC comentario : comentariosEvento){
			System.out.println("Código do comentário: " + comentario.getCodPessoa());
			System.out.println("Apelido do usuário que comentou: " + comentario.getApelido());
			System.out.println("Imagem de Perfil do usuário que comentou: " + comentario.getImgPerfil());
			System.out.println("Comentário: " + comentario.getComentario());
			System.out.println("Horá do comentário: " + comentario.getDataHora());
//			System.out.println("Código do comentário: " + comentario.getCodComentario());
//			System.out.println("Código da pessoa: " + comentario.getCodPessoa());
//			System.out.println("Código do evento: " + comentario.getCodEvento());
//			System.out.println("Comentário: " + comentario.getComentario());
		}
		if(comentariosEvento.size() == 0){
			System.out.println("Nenhum comentário encontrado para o evento!");
		}*/
		
		/*---------------------------------------------------*/
		
		//Moderadores:
		//Teste do método buscarModeradoresDoEvento(codEvento)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Pessoa> pessoas = eventoDAO.buscarModeradoresDoEvento(1);
		for(Pessoa pes : pessoas){
			System.out.println("Código: " + pes.getCodPessoa());
			System.out.println("Nome: " + pes.getNome());
			System.out.println("Apelido: " + pes.getApelido());
		}
		
		if(pessoas.size() == 0) {
			System.out.println("Nenhum resultado encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//buscarEventosDoUsuario(int codUsuario)
		//Teste do método buscarEventosDoUsuario(codUsuario)
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Evento> eventos = eventoDAO.buscarEventosDoUsuario(1);
		for(Evento eve : eventos){
			System.out.println("Código: " + eve.getCodEvento());
			System.out.println("Nome: " + eve.getNome());
			System.out.println("Imagem: " + eve.getImgEvento());
		}
		
		/*---------------------------------------------------*/
		
		//Moderadores:
		//Teste do método buscarEventos()
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Evento> eventos = eventoDAO.buscarEventos();
		for(Evento eve : eventos){
			System.out.println("Código: " + eve.getCodEvento());
			System.out.println("Nome: " + eve.getNome());
			System.out.println("Imagem: " + eve.getImgEvento());
		}*/
		
	}
	
}
