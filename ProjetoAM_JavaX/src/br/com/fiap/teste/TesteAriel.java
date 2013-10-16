package br.com.fiap.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Pessoa;

public class TesteAriel {
	
	public static void main(String[] args) {
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		//TELA ADICIONAR MEMBRO GRUPO
		//Teste do m�todo buscaUsuariosParaAdicionarAoGrupo(codGrupo)
		/*GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		List<Pessoa> pessoas = grupoDAO.buscarPessoasParaAdicionarAoGrupo(1);
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
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		Pessoa pessoaTeste = new Pessoa();
		pessoaTeste.setCodPessoa(1);
		List<Evento> eventos = eventoDAO.buscarEventosDaPessoa(pessoaTeste);
		for(Evento eve : eventos){
			System.out.println("C�digo: " + eve.getCodEvento());
			System.out.println("Nome: " + eve.getNome());
			System.out.println("Imagem: " + eve.getImgEvento());
		}*/
		
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
		
		/*---------------------------------------------------*/
		
		//TELA INDEX
		//Teste do m�todo buscarEmailESenha(email,senha)
		/*UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarEmailESenha("joao@gmail.com", "123");
		System.out.println(usuario.getCodUsuario());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getSenha());
		usuario = usuarioDAO.buscarEmailESenha("teste", "teste");
		if(usuario != null){
			System.out.println(usuario.getCodUsuario());
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getSenha());
		} else {
			System.out.println("Nenhum usu�rio encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA INDEX
		//Teste do m�todo buscarPorEmail(email)
		/*UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarPorEmail("joao@gmail.com");
		System.out.println(usuario.getCodUsuario());
		System.out.println(usuario.getEmail());
		System.out.println(usuario.getSenha());
		usuario = usuarioDAO.buscarPorEmail("teste");
		if(usuario != null){
			System.out.println(usuario.getCodUsuario());
			System.out.println(usuario.getEmail());
			System.out.println(usuario.getSenha());
		} else {
			System.out.println("Nenhum usu�rio encontrado!");
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA INDEX
		//Teste do m�todo buscarPorUsuario(usuario)
		/*PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarPorEmail("joao@gmail.com");
		Pessoa pessoa = pessoaDAO.buscarPorUsuario(usuario);
		System.out.println("C�d. Usu�rio: " + pessoa.getUsuario().getCodUsuario());
		System.out.println("C�d. Pessoa: " + pessoa.getCodPessoa());
		System.out.println("Nome: " + pessoa.getNome());
		System.out.println("Apelido: " + pessoa.getApelido());*/
		
		/*---------------------------------------------------*/
		
		//TELA GRUPOS
		//Teste do m�todo buscarGruposPorNome(nome)
		/*GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(1);
		List<Grupo> grupos = grupoDAO.buscarGruposVisiveisPorNome(pessoa,"a");
		for(Grupo grupo : grupos){
			System.out.println("C�d.: " + grupo.getCodGrupo());
			System.out.println("Nome: " + grupo.getNomeGrupo());
			System.out.println("Membros: " + grupo.getQuantidade());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA GRUPOS
		//Teste do m�todo buscarMeusGruposPorNome(pessoa, nome)
		/*GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarPorEmail("joao@gmail.com");
		Pessoa pessoa = pessoaDAO.buscarPorUsuario(usuario);
		List<Grupo> grupos = grupoDAO.buscarMeusGruposPorNome(pessoa, "a");
		for(Grupo grupo : grupos){
			System.out.println("C�d.: " + grupo.getCodGrupo());
			System.out.println("Nome: " + grupo.getNomeGrupo());
			System.out.println("Membros: " + grupo.getQuantidade());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA EVENTOS
		//Teste do m�todo buscarEventosPorNome(nome)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(1);
		List<Evento> eventos = eventoDAO.buscarEventosVisiveisPorNome(pessoa,"p");
		for(Evento eve : eventos){
			System.out.println("C�digo: " + eve.getCodEvento());
			System.out.println("Nome: " + eve.getNome());
			System.out.println("Qtd. Membros: " + eve.getQuantidade());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA GRUPOS
		//Teste do m�todo buscarMeusEventosPorNome(pessoa, nome)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = usuarioDAO.buscarPorEmail("ana@teste.com");
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		Pessoa pessoa = pessoaDAO.buscarPorUsuario(usuario);
		List<Evento> eventos = eventoDAO.buscarMeusEventosPorNome(pessoa, "a");
		for(Evento evento : eventos){
			System.out.println("C�d.: " + evento.getCodEvento());
			System.out.println("Nome: " + evento.getNome());
			System.out.println("Membros: " + evento.getQuantidade());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA CONVITE PEDIDO EVENTO
		//Teste do m�todo buscarConviteEventoPorPessoa(pessoa)
		/*ConviteEventoDAO conviteEventoDAO = new ConviteEventoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		List<ConviteEvento> convitesEvento = conviteEventoDAO.buscarConviteEventoPorPessoa(pessoa);
		for(ConviteEvento ce : convitesEvento){
			System.out.println("C�d.: " + ce.getCodConvite());
			System.out.println("Desc.: " + ce.getDescricao());
			System.out.println("C�d Pessoa: " + ce.getPessoa().getCodPessoa());
			System.out.println("C�d Evento: " + ce.getEvento().getCodEvento());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA CONVITE PEDIDO EVENTO
		//Teste do m�todo buscarConviteEventoPorPessoa(pessoa)
		/*PedidoEventoDAO pedidoEventoDAO = new PedidoEventoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(1);
		List<PedidoEvento> pedidosEvento = pedidoEventoDAO.buscarPedidosDeEventoPraPessoa(pessoa);
		for(PedidoEvento pe : pedidosEvento){
			System.out.println("C�d.: " + pe.getCodPedido());
			System.out.println("Desc.: " + pe.getDescricao());
			System.out.println("C�d. Evento: " + pe.getEvento().getCodEvento());
			System.out.println("C�d. Pessoa: " + pe.getPessoa().getCodPessoa());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA CONVITE PEDIDO GRUPO
		//Teste do m�todo buscarConviteGrupoPorPessoa(pessoa)
		/*ConviteGrupoDAO conviteGrupoDAO = new ConviteGrupoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		List<ConviteGrupo> convitesGrupo = conviteGrupoDAO.buscarConviteGrupoPorPessoa(pessoa);
		for(ConviteGrupo cg : convitesGrupo){
			System.out.println("C�d.: " + cg.getCodConvite());
			System.out.println("Desc.: " + cg.getDescricao());
			System.out.println("Grupo: " + cg.getGrupo().getNomeGrupo());
			System.out.println("Pessoa: " + cg.getPessoa().getApelido());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA CONVITE PEDIDO GRUPO
		//Teste do m�todo buscarPedidoGrupoPraPessoa 
		/*PedidoGrupoDAO pedidoGrupoDAO = new PedidoGrupoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		List<PedidoGrupo> pedidosGrupo = pedidoGrupoDAO.buscarPedidoGrupoPraPessoa(pessoa);
		for(PedidoGrupo pg : pedidosGrupo){
			System.out.println("C�d.: " + pg.getCodPedidoGrupo());
			System.out.println("Desc.: " + pg.getDescricao());
			System.out.println("Pessoa: " + pg.getPessoa().getApelido());
			System.out.println("Grupo: " + pg.getGrupo().getNomeGrupo());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA HOME
		//Teste do m�todo buscarPopularidade()
		/*EsporteDAO esporteDAO = new EsporteDAOImpl(em);
		List<ItemGrafico> itens = esporteDAO.buscarPopularidade();
		int cont = 0;
		for(ItemGrafico ig : itens){
			cont++;
			System.out.println("Item n�mero: " + cont + ". Esporte: " + ig.getNome() + ". Qtd: " + ig.getQtd());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA MENSAGENS
		//Teste do m�todo buscarMensagensLidasDaPessoa(pessoa)
		/*Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		MensagemEventoDAO msgEventoDAO = new MensagemEventoDAOImpl(em);
		List<MensagemEvento> msgsEvento = msgEventoDAO.buscarMensagensDaPessoa(pessoa);
		for(MensagemEvento me : msgsEvento){
			System.out.println("C�d: " + me.getCodMensagem());
			System.out.println("Desc.: " + me.getDescricao());
			System.out.println("C�d. Pessoa: " + me.getPessoa().getCodPessoa());
			System.out.println("C�d. Evento: " + me.getEvento().getCodEvento());
			System.out.println("Confirmacao: " + me.getConfirmacao());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA MENSAGENS
		//Teste do m�todo buscarMensagensNaoLidasDaPessoa(pessoa)
		/*Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		MensagemEventoDAO msgEventoDAO = new MensagemEventoDAOImpl(em);
		List<MensagemEvento> msgsEvento = msgEventoDAO.buscarMensagensNaoLidasDaPessoa(pessoa);
		for(MensagemEvento me : msgsEvento){
			System.out.println("C�d: " + me.getCodMensagem());
			System.out.println("Desc.: " + me.getDescricao());
			System.out.println("C�d. Pessoa: " + me.getPessoa().getCodPessoa());
			System.out.println("C�d. Evento: " + me.getEvento().getCodEvento());
			System.out.println("Confirmacao: " + me.getConfirmacao());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA MENSAGENS
		//Teste do m�todo buscarMensagensLidasDaPessoa(pessoa)
		/*Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		MensagemGrupoDAO msgGrupoDAO = new MensagemGrupoDAOImpl(em);
		List<MensagemGrupo> msgsGrupo= msgGrupoDAO.buscarMensagensDaPessoa(pessoa);
		for(MensagemGrupo mg : msgsGrupo){
			System.out.println("C�d: " + mg.getCodMensagem());
			System.out.println("Desc.: " + mg.getDescricao());
			System.out.println("C�d. Pessoa: " + mg.getPessoa().getCodPessoa());
			System.out.println("C�d. Grupo: " + mg.getGrupo().getCodGrupo());
			System.out.println("Confirmacao: " + mg.getConfirmacao());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA MENSAGENS
		//Teste do m�todo buscarMensagensNaoLidasDaPessoa(pessoa)
		/*Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		MensagemGrupoDAO msgGrupoDAO = new MensagemGrupoDAOImpl(em);
		List<MensagemGrupo> msgsGrupo= msgGrupoDAO.buscarMensagensNaoLidasDaPessoa(pessoa);
		for(MensagemGrupo mg : msgsGrupo){
			System.out.println("C�d: " + mg.getCodMensagem());
			System.out.println("Desc.: " + mg.getDescricao());
			System.out.println("C�d. Pessoa: " + mg.getPessoa().getCodPessoa());
			System.out.println("C�d. Grupo: " + mg.getGrupo().getCodGrupo());
			System.out.println("Confirmacao: " + mg.getConfirmacao());
		}*/
		
		/*---------------------------------------------------*/
		
		//TELA GRUPOS
		//Teste do m�todo buscarGruposVisiveis(pessoa)
		/*GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(2);
		List<Grupo> itens = grupoDAO.buscarGruposVisiveis(pessoa);
		for(Grupo gru : itens){
			System.out.println("Nome: " + gru.getNomeGrupo());
		}
		System.out.println("Total: " + itens.size());*/
		
		/*---------------------------------------------------*/
		
		//TELA GRUPOS
		//Teste do m�todo buscarGruposVisiveis(pessoa)
		/*EventoDAO eventoDAO = new EventoDAOImpl(em);
		Pessoa pessoa = new Pessoa();
		pessoa.setCodPessoa(3);
		List<Evento> itens = eventoDAO.buscarEventosVisiveis(pessoa);
		for(Evento eve : itens){
			System.out.println("Nome: " + eve.getNome());
		}
		System.out.println("Total: " + itens.size());*/
				
		/*---------------------------------------------------*/
				
		
		PessoaDAO pesDAO = new PessoaDAOImpl(em);
		List<Pessoa> lista = pesDAO.buscarPorApelido("Jo");
		if(lista.size() == 0){
			System.out.println("Certo");
		} else {
			System.out.println("Errado!");
		}
	}
	
}
