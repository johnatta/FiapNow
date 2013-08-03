package br.com.fiap.testeInserts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioEventoDAO;
import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.dao.ModeradorEventoDAO;
import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioEventoDAOImpl;
import br.com.fiap.daoimpl.ConviteEventoDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.MensagemEventoDAOImpl;
import br.com.fiap.daoimpl.ModeradorEventoDAOImpl;
import br.com.fiap.daoimpl.PedidoEventoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioEvento;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemEvento;
import br.com.fiap.entity.ModeradorEvento;
import br.com.fiap.entity.PedidoEvento;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.entity.Usuario;

public class ConsoleViewEvento {
	
	public static void main(String[] args) {
		Calendar dtNascimento = Calendar.getInstance();
		dtNascimento.set(30, 8, 22);
		Calendar dtNascimentoA = Calendar.getInstance();
		dtNascimentoA.set(30, 6, 12);
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		PedidoEventoDAO pedEventoDAO = new PedidoEventoDAOImpl(em);
		ModeradorEventoDAO modEventoDAO = new ModeradorEventoDAOImpl(em);
		ComentarioEventoDAO comentEventoDAO = new ComentarioEventoDAOImpl(em);
		MensagemEventoDAO msgEventoDAO = new MensagemEventoDAOImpl(em);
		ConviteEventoDAO convtEventoDAO = new ConviteEventoDAOImpl(em);
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		
		List<Esporte> esportes = new ArrayList<Esporte>();

		Esporte esporteE = new Esporte();
		esporteE.setNome("HandBall");
		esportes.add(esporteE);
		
		Esporte esporteB = new Esporte();
		esporteB.setNome("Futebol");
		esportes.add(esporteB);
		
		Esporte esporteC = new Esporte();
		esporteC.setNome("Futsal");
		esportes.add(esporteC);
		
		List<Grupo> grupos = new ArrayList<Grupo>();
		
		Grupo grupoA = new Grupo();
		grupoA.setNomeGrupo("Somente Amigos");
		grupoA.setDescricao("Grupo formado por amigos da rua Max Planck");
		grupoA.setPrivacidade(Privacidade.Fechado);
		grupoA.setImgGrupo(new byte[3]);
		grupoA.setEsportes(esportes);
		grupos.add(grupoA);
		
		Grupo grupoB = new Grupo();
		grupoB.setNomeGrupo("Futebol Arte");
		grupoB.setDescricao("Grupo formado para aqueles que apreciam um Futebol de qualidade");
		grupoB.setPrivacidade(Privacidade.Aberto);
		grupoB.setImgGrupo(new byte[4]);
		grupoB.setEsportes(esportes);
		grupos.add(grupoB);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Joao");
		pessoa.setSobrenome("Mendes");
		pessoa.setDtNasc(dtNascimento);
		pessoa.setApelido("JM");
		pessoa.setTelRes("01120345323");
		pessoa.setCel("011975641290");
		pessoa.setImgPerfil(new byte[3]);
		pessoa.setImgBackGround(new byte[5]);
		pessoa.setEsportes(esportes);
		pessoa.setGrupos(grupos);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("joaomendes.jm@gmail.com");
		usuario.setSenha("joaoJM30");
		pessoa.setCodUsuario(usuario);
		
		Endereco enderecoP = new Endereco();
		enderecoP.setPais("Brasil");
		enderecoP.setEstado("SP");
		enderecoP.setCidade("São Paulo");
		enderecoP.setBairro("Curuça");
		enderecoP.setCep("03701000");
		enderecoP.setRua("Max Planck");
		enderecoP.setNumero(160);
		enderecoP.setComplemento(null);
		enderecoP.setLatitude((float) -23.601981);
		enderecoP.setLongitude((float) -46.667178);
		pessoa.setCodEndereco(enderecoP);
		
		pessoaDAO.insert(pessoa); 
		
		Evento evento = new Evento();
		evento.setNome("Futebol Amigo");
		evento.setDescricao("Futebol para reunir a galerinha do futiba pra lembrar dos velhos tempos.");
		evento.setPrivacidade(Privacidade.Fechado);
		evento.setCusto(90);
		evento.setTelContato("01120482159");
		evento.setImgEvento(new byte [4]);
		evento.setDtEvento(dtNascimento);
		evento.setGrupos(grupos);
		
		Esporte esporteA = new Esporte();
		esporteA.setNome("Futebol");
		evento.setCodEsporte(esporteA);
		
		Endereco endereco = new Endereco();
		endereco.setPais("Brasil");
		endereco.setEstado("SP");
		endereco.setCidade("São Paulo");
		endereco.setBairro("Vila Ribeiro de Barros");
		endereco.setCep("05307200");
		endereco.setRua("Av. Embaixador Macedo Soares");
		endereco.setNumero(8000);
		endereco.setComplemento(null);
		endereco.setLatitude((float) -23.51734);
		endereco.setLongitude((float) -46.730175);
		evento.setCodEndereco(endereco);
		
		eventoDAO.insert(evento); 
		
		// PEDIDO EVENTO
		PedidoEvento pedidoEvento = new PedidoEvento();
		pedidoEvento.setDescricao("Eu desejo participar.");
		pedidoEvento.setCodEvento(evento);
		pedidoEvento.setCodPessoa(pessoa);
		pedEventoDAO.insert(pedidoEvento);  

		// MODERADOR EVENTO
		ModeradorEvento modEvento = new ModeradorEvento();
		modEvento.setCodEvento(evento);
		modEvento.setCodPessoa(pessoa);
		modEventoDAO.insert(modEvento);  
		
		// COMENTARIO EVENTO
		ComentarioEvento comentEvento = new ComentarioEvento();
		comentEvento.setComentario("Estou esperando todos na porta da quadra");
		comentEvento.setDtHora(Calendar.getInstance());
		comentEvento.setCodEvento(evento);
		comentEvento.setCodPessoa(pessoa);
		comentEventoDAO.insert(comentEvento);
		
		// MENSAGEM EVENTO
		MensagemEvento msgEvento = new MensagemEvento();
		msgEvento.setDescricao("Briga e baixaria nao sera permitido. Sujeito a sair no meio do evento");
		msgEvento.setConfirmacao(Confirmacao.SIM);
		msgEvento.setCodEvento(evento);
		msgEvento.setCodPessoa(pessoa);
		msgEventoDAO.insert(msgEvento); 
		
		// CONVITE EVENT0
		ConviteEvento convtEvento = new ConviteEvento();
		convtEvento.setDescricao("Espero você para um futebol digno.");
		convtEvento.setCodEvento(evento);
		convtEvento.setCodPessoa(pessoa);
		convtEventoDAO.insert(convtEvento); 
		
		/*
		//Buscando por ID
		Evento eve = new Evento();
		eve = eventoDAO.searchByID(2);
		System.out.println("Nome: " + eve.getNome() );
		
		//Atualizando
		Evento eventoN = new Evento();
		eventoN.setCodEvento(2);
		eventoN.setNome("Polo Amigo");
		eventoN.setDescricao("Para todos os apreciadores do POLO");
		eventoN.setCusto(45);
		eventoN.setDtEvento(Calendar.getInstance());
		eventoN.setImgEvento(new byte [6]);
		eventoN.setPrivacidade(Privacidade.Aberto);
		eventoN.setTelContato("01130687456");
		eventoN.setGrupos(grupos);
		eventoN.setCodEndereco(enderecoP);
		eventoN.setCodEsporte(esporteA);
		eventoDAO.update(eventoN);
		

		//Removendo pela entidade
		//Inserido a pessoa para após deletar
		Evento eventoD = new Evento();
		eventoD.setNome("Polo Amigo");
		eventoD.setDescricao("Para todos os apreciadores do POLO");
		eventoD.setCusto(45);
		eventoD.setDtEvento(Calendar.getInstance());
		eventoD.setImgEvento(new byte [6]);
		eventoD.setPrivacidade(Privacidade.Aberto);
		eventoD.setTelContato("01130687456");
		eventoD.setGrupos(grupos);
		eventoD.setCodEndereco(enderecoP);
		eventoD.setCodEsporte(esporteA);
		
		eventoDAO.insert(eventoD); 
		eventoD.setCodEvento(4);
		eventoD = eventoDAO.searchByID(eventoD.getCodEvento());
		eventoDAO.remove(eventoD);
		
		//Removendo por Id
		eventoDAO.removeById(3);
		*/
	}
}
