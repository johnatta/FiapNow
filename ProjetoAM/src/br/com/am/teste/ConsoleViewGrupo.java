package br.com.am.teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.am.dao.ComentarioGrupoDAO;
import br.com.am.dao.ConviteGrupoDAO;
import br.com.am.dao.EntityManagerFactorySingleton;
import br.com.am.dao.GrupoDAO;
import br.com.am.dao.MensagemGrupoDAO;
import br.com.am.dao.ModeradorGrupoDAO;
import br.com.am.dao.PedidoGrupoDAO;
import br.com.am.daoimpl.ComentarioGrupoDAOImpl;
import br.com.am.daoimpl.ConviteGrupoDAOImpl;
import br.com.am.daoimpl.GrupoDAOImpl;
import br.com.am.daoimpl.MensagemGrupoDAOImpl;
import br.com.am.daoimpl.ModeradorGrupoDAOImpl;
import br.com.am.daoimpl.PedidoGrupoDAOImpl;
import br.com.am.entity.ComentarioGrupo;
import br.com.am.entity.Confirmacao;
import br.com.am.entity.ConviteGrupo;
import br.com.am.entity.Esporte;
import br.com.am.entity.Grupo;
import br.com.am.entity.MensagemGrupo;
import br.com.am.entity.ModeradorGrupo;
import br.com.am.entity.PedidoGrupo;
import br.com.am.entity.Pessoa;
import br.com.am.entity.Privacidade;

public class ConsoleViewGrupo {
	
	public static void main(String[] args) {
		
		Pessoa pessoa = new Pessoa();
		
		List<Esporte> esportes = new ArrayList<Esporte>();
		
		Esporte esporteA = new Esporte();
		esporteA.setNome("Futebol Americano");
		esportes.add(esporteA);
		
		Esporte esporteB = new Esporte();
		esporteB.setNome("Futebol Society");
		esportes.add(esporteB);
		
		Esporte esporteC = new Esporte();
		esporteC.setNome("Rugby");
		esportes.add(esporteC);
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		ConviteGrupoDAO convGrupoDAO = new ConviteGrupoDAOImpl(em);
		ComentarioGrupoDAO comGrupoDAO = new ComentarioGrupoDAOImpl(em);
		MensagemGrupoDAO menGrupoDAO = new MensagemGrupoDAOImpl(em);
		ModeradorGrupoDAO modGrupoDAO = new ModeradorGrupoDAOImpl(em);
		PedidoGrupoDAO pedGrupoDAO = new PedidoGrupoDAOImpl(em);
		
		List<Grupo> grupos = new ArrayList<Grupo>();
		
		Grupo grupo = new Grupo();
		grupo.setDescricao("Um Novo grupo");
		grupo.setNomeGrupo("Vai Curintia");
		grupo.setPrivacidade(Privacidade.Fechado);
		grupo.setEsportes(esportes);
		
		ConviteGrupo convGrupo = new ConviteGrupo();
		convGrupo.setDescricao("Entre para o meu Grupo CARA =D");
		convGrupo.setCodPessoa(pessoa);
		convGrupo.setCodGrupo(grupo);
		
		ComentarioGrupo comGrupo = new ComentarioGrupo();
		comGrupo.setComentario("Futebol sabado Galera");
		comGrupo.setDataHora(Calendar.getInstance());
		comGrupo.setCodGrupo(grupo);
		comGrupo.setCodPessoa(pessoa);
		
		MensagemGrupo menGrupo = new MensagemGrupo();
		menGrupo.setConfirmacao(Confirmacao.SIM);
		menGrupo.setDescricao("Bem Vindo ao Grupo");
		menGrupo.setCodGrupo(grupo);
		menGrupo.setCodPessoa(pessoa);
		
		ModeradorGrupo modGrupo = new ModeradorGrupo();
		modGrupo.setCodGrupo(grupo);
		modGrupo.setCodPessoa(pessoa);
		
		PedidoGrupo pedGrupo = new PedidoGrupo();
		pedGrupo.setDescricao("Voce aceitaria entra no meu grupo?");
		pedGrupo.setCodGrupo(grupo);
		pedGrupo.setCodPessoa(pessoa);
		
		grupos.add(grupo);
		
		grupoDAO.insert(grupo);
		convGrupoDAO.insert(convGrupo);
		comGrupoDAO.insert(comGrupo);
		menGrupoDAO.insert(menGrupo);
		modGrupoDAO.insert(modGrupo);
		pedGrupoDAO.insert(pedGrupo);
		
	}
	
}
