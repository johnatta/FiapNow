package br.com.fiap.testeInserts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioEventoDAO;
import br.com.fiap.dao.ConviteEventoDAO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemEventoDAO;
import br.com.fiap.dao.PedidoEventoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioEventoDAOImpl;
import br.com.fiap.daoimpl.ConviteEventoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemEventoDAOImpl;
import br.com.fiap.daoimpl.PedidoEventoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioEvento;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.ConviteEvento;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.EventoGrupo;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemEvento;
import br.com.fiap.entity.PedidoEvento;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;

public class ConsoleViewEvento02 {
	
	public static void main(String[] args) {
		Calendar data = Calendar.getInstance();
		data.set(2013,10, 01);
		Calendar dataA = Calendar.getInstance();
		dataA.set(2013,10, 02);
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		PedidoEventoDAO pedEventoDAO = new PedidoEventoDAOImpl(em);
		ComentarioEventoDAO comentEventoDAO = new ComentarioEventoDAOImpl(em);
		MensagemEventoDAO msgEventoDAO = new MensagemEventoDAOImpl(em);
		ConviteEventoDAO convtEventoDAO = new ConviteEventoDAOImpl(em);
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		EsporteDAO esporteDAO = new EsporteDAOImpl(em);
		List<Esporte> esportes = new ArrayList<Esporte>();
		List<Esporte> esportesA = new ArrayList<Esporte>();
		List<EventoGrupo> eventoGrupos = new ArrayList<EventoGrupo>();
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		Esporte espEvent = new Esporte();
		Pessoa admEvent = new Pessoa();
		
		List<Grupo> grupos = new ArrayList<Grupo>();
		Pessoa pADM = new Pessoa();
		Pessoa pesADM = new Pessoa();

		Esporte esporteE = new Esporte();
		esporteE = espDAO.searchByID(4);
		esportes.add(esporteE);
		
		//Lista de esportesA
		Esporte esporteF = new Esporte();
		esporteF = espDAO.searchByID(8);
		esportesA.add(esporteF);
		
		Grupo grupoA = new Grupo();
		grupoA.setNomeGrupo("House of lions");
		grupoA.setDescricao("Grupo para os apaixonados de esportes radicais de rua.");
		grupoA.setPrivacidade(Privacidade.Aberto);
		grupoA.setImgGrupo(new byte[3]);
		grupoA.setEsportes(esportes);
		pesADM = pessoaDAO.searchByID(3);
		grupoA.setAdm(pesADM);
		grupos.add(grupoA);
		
		Grupo grupoB = new Grupo();
		grupoB.setNomeGrupo("Set Point");
		grupoB.setDescricao("Grupo formado para adeptos do Volleyball tanto de quadra como de praia.");
		grupoB.setPrivacidade(Privacidade.Aberto);
		grupoB.setImgGrupo(new byte[4]);
		grupoB.setEsportes(esportesA);
		pADM = pessoaDAO.searchByID(2);
		grupoB.setAdm(pADM);
		
		Evento evento = new Evento();
		evento.setNome("Skate Long - Ipiranga");
		evento.setDescricao("Andar de Long nas imedia��s do Museu do Ipiranga, clima familiar e hist�rico.");
		evento.setPrivacidade(Privacidade.Aberto);
		evento.setCusto(0);
		evento.setTelContato("01150652999");
		evento.setImgEvento(new byte [4]);
		evento.setDtEvento(data);
		evento.setGrupos(eventoGrupos);
		admEvent = pessoaDAO.searchByID(1);
		evento.setAdm(admEvent);
		espEvent = espDAO.searchByID(4);
		evento.setCodEsporte(espEvent);
		
		Endereco endereco = new Endereco();
		endereco.setPais("Brasil");
		endereco.setEstado("SP");
		endereco.setCidade("S�o Paulo");
		endereco.setBairro("Ipiranga");
		endereco.setCep("04263000");
		endereco.setRua("Av. Nazareth");
		endereco.setNumero(0000);
		endereco.setComplemento("Museu do Ipiranga");
		endereco.setLatitude((float) -23.579745);
		endereco.setLongitude((float) -46.610244);
		evento.setCodEndereco(endereco);
		eventoDAO.insert(evento);
		
		//Adicionando Grupo Cod 1 para teste de Exclus�o do Evento
		GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		grupos = new ArrayList<Grupo>();
		grupos.add(grupoDAO.searchByID(2));
		
		for(Grupo gru : grupos){
			EventoGrupo eg = new EventoGrupo();
			eg.setGrupo(gru);
			eg.setEvento(evento);
			eventoGrupos.add(eg);
		}
		
		evento.setGrupos(eventoGrupos);

		//Buscando por ID - PESSOA
		Pessoa pes = new Pessoa();
		pes = pessoaDAO.searchByID(3);
		
		PedidoEvento pedidoEvento = new PedidoEvento();
		pedidoEvento.setDescricao("Eu desejo participar.");
		pedidoEvento.setEvento(eventoDAO.searchByID(2));
		pedidoEvento.setPessoa(pes);
		pedEventoDAO.insert(pedidoEvento);  

		/*ModeradorEvento modEvento = new ModeradorEvento();
		modEvento.setEvento(evento);
		modEvento.setPessoa(pes);
		modEventoDAO.insert(modEvento);*/
		
		//Ariel Molina - Nova forma de incluir moderador
		Pessoa moderador = new Pessoa();
		moderador = pessoaDAO.searchByID(2);
		if(evento.getModeradores() == null){
			List<Pessoa> moderadores = new ArrayList<Pessoa>();
			moderadores.add(moderador);
			evento.setModeradores(moderadores);
		}else {
			evento.getModeradores().add(moderador);
		}
		
		ComentarioEvento comentEvento = new ComentarioEvento();
		comentEvento.setComentario("Aguardando todos ao meio dia em frente ao monumento.");
		comentEvento.setDtHora(Calendar.getInstance());
		comentEvento.setEvento(evento);
		comentEvento.setPessoa(pes);
		comentEventoDAO.insert(comentEvento);
		
		MensagemEvento msgEvento = new MensagemEvento();
		msgEvento.setTitulo("Lembrete");
		msgEvento.setDescricao("Favor lembre-se de levar protetores para a pr�tica do long.");
		msgEvento.setConfirmacao(Confirmacao.SIM);
		msgEvento.setEvento(evento);
		msgEvento.setCodPessoa(pes);
		msgEventoDAO.insert(msgEvento); 
		
		ConviteEvento convtEvento = new ConviteEvento();
		convtEvento.setDescricao("Voc� foi convidado para o Evento de skate no Museu do Ipiranga.");
		convtEvento.setEvento(evento);
		convtEvento.setPessoa(pes);
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
		eventoN.setCodEndereco(endereco);
		eventoN.setCodEsporte(esporteA);
		eventoDAO.update(eventoN);
		

		//Removendo pela entidade
		//Inserido a pessoa para ap�s deletar
		Evento eventoD = new Evento();
		eventoD.setNome("Polo Amigo");
		eventoD.setDescricao("Para todos os apreciadores do POLO");
		eventoD.setCusto(45);
		eventoD.setDtEvento(Calendar.getInstance());
		eventoD.setImgEvento(new byte [6]);
		eventoD.setPrivacidade(Privacidade.Aberto);
		eventoD.setTelContato("01130687456");
		eventoD.setGrupos(grupos);
		eventoD.setCodEndereco(endereco);
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
