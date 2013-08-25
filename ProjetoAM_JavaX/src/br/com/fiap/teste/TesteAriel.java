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
		//Teste do m�todo buscaUsuariosParaAdicionarAoGrupo(codGrupo)
		/*GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		List<Pessoa> pessoas = grupoDAO.buscarUsuariosParaAdicionarAoGrupo(1);
		for(Pessoa pes : pessoas){
			System.out.println("C�digo da pessoa que n�o est� neste evento: " + pes.getCodPessoa());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA EVENTO
		//Informa��es do Evento
		//Teste do m�todo buscarPeloCodigo(int codEvento)
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		Evento evento = eventoDAO.buscarPeloCodigo(1);
		System.out.println("C�digo do Evento: " + evento.getCodEvento());
		System.out.println("Descri��o do Evento: " + evento.getDescricao());
		System.out.println("Custo: " + evento.getCusto());
		System.out.println("Data do Evento: " + sdf.format(evento.getDtEvento().getTime()));*/
		
		/*---------------------------------------------------*/
		
		//Membros
		//Teste do m�todo buscarMembrosPorEvento(codEvento)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Pessoa> pessoas = eventoDAO.buscarMembrosPorEvento(1);
		for(Pessoa pes : pessoas){
			System.out.println("C�digo: " + pes.getCodPessoa());
			System.out.println("Nome: " + pes.getNome());
			System.out.println("Apelido: " + pes.getApelido());
		}
		
		if(pessoas.size() == 0) {
			System.out.println("Nenhum resultado encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//Coment�rios
		//Teste do m�todo buscarComentariosPeloEvento(int codEvento)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<ComentarioEventoRC> comentariosEvento = eventoDAO.buscarComentariosPeloEvento(2);
		for(ComentarioEventoRC comentario : comentariosEvento){
			System.out.println("C�digo do coment�rio: " + comentario.getCodPessoa());
			System.out.println("Apelido do usu�rio que comentou: " + comentario.getApelido());
			System.out.println("Imagem de Perfil do usu�rio que comentou: " + comentario.getImgPerfil());
			System.out.println("Coment�rio: " + comentario.getComentario());
			System.out.println("Hor� do coment�rio: " + comentario.getDataHora());
//			System.out.println("C�digo do coment�rio: " + comentario.getCodComentario());
//			System.out.println("C�digo da pessoa: " + comentario.getCodPessoa());
//			System.out.println("C�digo do evento: " + comentario.getCodEvento());
//			System.out.println("Coment�rio: " + comentario.getComentario());
		}
		if(comentariosEvento.size() == 0){
			System.out.println("Nenhum coment�rio encontrado para o evento!");
		}*/
		
		/*---------------------------------------------------*/
		
		//Moderadores:
		//Teste do m�todo buscarModeradoresDoEvento(codEvento)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Pessoa> pessoas = eventoDAO.buscarModeradoresDoEvento(1);
		for(Pessoa pes : pessoas){
			System.out.println("C�digo: " + pes.getCodPessoa());
			System.out.println("Nome: " + pes.getNome());
			System.out.println("Apelido: " + pes.getApelido());
		}
		
		if(pessoas.size() == 0) {
			System.out.println("Nenhum resultado encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//buscarEventosDoUsuario(int codUsuario)
		//Teste do m�todo buscarEventosDoUsuario(codUsuario)
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Evento> eventos = eventoDAO.buscarEventosDoUsuario(1);
		for(Evento eve : eventos){
			System.out.println("C�digo: " + eve.getCodEvento());
			System.out.println("Nome: " + eve.getNome());
			System.out.println("Imagem: " + eve.getImgEvento());
		}
		
		/*---------------------------------------------------*/
		
		//Moderadores:
		//Teste do m�todo buscarEventos()
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Evento> eventos = eventoDAO.buscarEventos();
		for(Evento eve : eventos){
			System.out.println("C�digo: " + eve.getCodEvento());
			System.out.println("Nome: " + eve.getNome());
			System.out.println("Imagem: " + eve.getImgEvento());
		}*/
		
	}
	
}
