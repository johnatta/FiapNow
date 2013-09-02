package br.com.fiap.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.daoimpl.PedidoEventoDAOImpl;
import br.com.fiap.entity.PedidoEvento;
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
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		Pessoa pessoaTeste = new Pessoa();
		pessoaTeste.setCodPessoa(1);
		List<Evento> eventos = eventoDAO.buscarEventosDaPessoa(pessoaTeste);
		for(Evento eve : eventos){
			System.out.println("Código: " + eve.getCodEvento());
			System.out.println("Nome: " + eve.getNome());
			System.out.println("Imagem: " + eve.getImgEvento());
		}*/
		
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
		
		/*---------------------------------------------------*/
		
		//TELA INDEX
		//Teste do método buscarEmailESenha(email,senha)
		/*UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarEmailESenha("barbara.alves@hotmail.com", "barbaraII");
		System.out.println(usuario.getCodUsuario());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getSenha());
		usuario = usuarioDAO.buscarEmailESenha("teste", "teste");
		if(usuario != null){
			System.out.println(usuario.getCodUsuario());
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getSenha());
		} else {
			System.out.println("Nenhum usuário encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA INDEX
		//Teste do método buscarPorEmail(email)
		/*UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarPorEmail("barbara.alves@hotmail.com");
		System.out.println(usuario.getCodUsuario());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getSenha());
		usuario = usuarioDAO.buscarPorEmail("teste");
		if(usuario != null){
			System.out.println(usuario.getCodUsuario());
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getSenha());
		} else {
			System.out.println("Nenhum usuário encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA INDEX
		//Teste do método buscarPorUsuario(usuario)
		/*PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarPorEmail("barbara.alves@hotmail.com");
		Pessoa pessoa = pessoaDAO.buscarPorUsuario(usuario);
		System.out.println("Cód. Usuário: " + pessoa.getUsuario().getCodUsuario());
		System.out.println("Cód. Pessoa: " + pessoa.getCodPessoa());
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("Apelido: " + pessoa.getApelido());*/
		
		/*---------------------------------------------------*/
		
		//TELA GRUPOS
		//Teste do método buscarGruposPorNome(nome)
		/*GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		List<Grupo> grupos = grupoDAO.buscarGruposPorNome("a");
		for(Grupo grupo : grupos){
			System.out.println("Cód.: " + grupo.getCodGrupo());
			System.out.println("Nome: " + grupo.getNomeGrupo());
			System.out.println("Membros: " + grupo.getQuantidade());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA GRUPOS
		//Teste do método buscarMeusGruposPorNome(pessoa, nome)
		/*GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarPorEmail("barbara.alves@hotmail.com");
		Pessoa pessoa = pessoaDAO.buscarPorUsuario(usuario);
		List<Grupo> grupos = grupoDAO.buscarMeusGruposPorNome(pessoa, "a");
		for(Grupo grupo : grupos){
			System.out.println("Cód.: " + grupo.getCodGrupo());
			System.out.println("Nome: " + grupo.getNomeGrupo());
			System.out.println("Membros: " + grupo.getQuantidade());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA EVENTOS
		//Teste do método buscarEventosPorNome(nome)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Evento> eventos = eventoDAO.buscarEventosPorNome("p");
		for(Evento eve : eventos){
			System.out.println("Código: " + eve.getCodEvento());
			System.out.println("Nome: " + eve.getNome());
			System.out.println("Qtd. Membros: " + eve.getQuantidade());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA GRUPOS
		//Teste do método buscarMeusEventosPorNome(pessoa, nome)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarPorEmail("barbara.alves@hotmail.com");
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		Pessoa pessoa = pessoaDAO.buscarPorUsuario(usuario);
		List<Evento> eventos = eventoDAO.buscarMeusEventosPorNome(pessoa, "a");
		for(Evento evento : eventos){
			System.out.println("Cód.: " + evento.getCodEvento());
			System.out.println("Nome: " + evento.getNome());
			System.out.println("Membros: " + evento.getQuantidade());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA CONVITE PEDIDO EVENTO
		//Teste do método buscarConviteEventoPorPessoa(pessoa)
		/*ConviteEventoDAO conviteEventoDAO = new ConviteEventoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		List<ConviteEvento> convitesEvento = conviteEventoDAO.buscarConviteEventoPorPessoa(pessoa);
		for(ConviteEvento ce : convitesEvento){
			System.out.println("Cód.: " + ce.getCodConvite());
			System.out.println("Desc.: " + ce.getDescricao());
			System.out.println("Cód Pessoa: " + ce.getPessoa().getCodPessoa());
			System.out.println("Cód Evento: " + ce.getEvento().getCodEvento());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA CONVITE PEDIDO EVENTO
		//Teste do método buscarConviteEventoPorPessoa(pessoa)
		PedidoEventoDAO pedidoEventoDAO = new PedidoEventoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(1);
		List<PedidoEvento> pedidosEvento = pedidoEventoDAO.buscarPedidosDeEventoPraPessoa(pessoa);
		for(PedidoEvento pe : pedidosEvento){
			System.out.println("Cód.: " + pe.getCodPedido());
			System.out.println("Desc.: " + pe.getDescricao());
			System.out.println("Cód. Evento: " + pe.getEvento().getCodEvento());
			System.out.println("Cód. Pessoa: " + pe.getPessoa().getCodPessoa());
		}
		
	}
	
}
