package br.com.fiap.teste;

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
		esporteE.setNome("Vôlei");
		esportes.add(esporteE);
		
		Esporte esporteB = new Esporte();
		esporteB.setNome("Tênis");
		esportes.add(esporteB);
		
		Esporte esporteC = new Esporte();
		esporteC.setNome("Futebol Americano");
		esportes.add(esporteC);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Douglas");
		pessoa.setSobrenome("Fercondini");
		pessoa.setDtNasc(dtNascimento);
		pessoa.setApelido("Dado");
		pessoa.setTelRes("01156707013");
		pessoa.setCel("011977073781");
		pessoa.setImgPerfil(new byte[3]);
		pessoa.setImgBackGround(new byte[5]);
		pessoa.setEsportes(esportes);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("douglas_fercondini@gmail.com");
		usuario.setSenha("dadoFercon");
		pessoa.setCodUsuario(usuario);
		
		Endereco enderecoP = new Endereco();
		enderecoP.setPais("Brasil");
		enderecoP.setEstado("SP");
		enderecoP.setCidade("São Paulo");
		enderecoP.setBairro("Indianópolis");
		enderecoP.setCep("04515030");
		enderecoP.setRua("Avenida Jacutinga");
		enderecoP.setNumero(336);
		enderecoP.setComplemento("apto. 13");
		enderecoP.setLatitude((float) -23.601981);
		enderecoP.setLongitude((float) -46.667178);
		pessoa.setCodEndereco(enderecoP);
		
		pessoaDAO.insert(pessoa); 
		
		Evento evento = new Evento();
		evento.setNome("Torneio Touchdown");
		evento.setDescricao("Iremos fazer um torneio entre nós para eleger a melhor equipe.");
		evento.setPrivacidade(Privacidade.Fechado);
		evento.setCusto(90);
		evento.setTelContato("01167890988");
		evento.setImgEvento(new byte [4]);
		evento.setDtEvento(dtNascimento);
		evento.setGrupos(null);
		
		Esporte esporteA = new Esporte();
		esporteA.setNome("Futebol Americano");
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
		
		PedidoEvento pedidoEvento = new PedidoEvento();
		pedidoEvento.setDescricao("Eu desejo participar.");
		pedidoEvento.setCodEvento(evento);
		pedidoEvento.setCodPessoa(pessoa);
		pedEventoDAO.insert(pedidoEvento);  

		ModeradorEvento modEvento = new ModeradorEvento();
		modEvento.setCodEvento(evento);
		modEvento.setCodPessoa(pessoa);
		modEventoDAO.insert(modEvento);  
		
		ComentarioEvento comentEvento = new ComentarioEvento();
		comentEvento.setComentario("Estou com dúvida sobre a forma de classificação das equipes. Alguém sabe mais sobre isso ?");
		comentEvento.setDtHora(Calendar.getInstance());
		comentEvento.setCodEvento(evento);
		comentEvento.setCodPessoa(pessoa);
		comentEventoDAO.insert(comentEvento);
		
		MensagemEvento msgEvento = new MensagemEvento();
		msgEvento.setDescricao("O vestiário do lado A está em reforma. No dia usaremos apenas o lado B");
		msgEvento.setConfirmacao(Confirmacao.SIM);
		msgEvento.setCodEvento(evento);
		msgEvento.setCodPessoa(pessoa);
		msgEventoDAO.insert(msgEvento); 
		
		ConviteEvento convtEvento = new ConviteEvento();
		convtEvento.setDescricao("Espero você para termos um ótimo torneio.");
		convtEvento.setCodEvento(evento);
		convtEvento.setCodPessoa(pessoa);
		convtEventoDAO.insert(convtEvento); 
	}
}
