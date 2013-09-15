package br.com.fiap.testeInserts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.EventoDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Evento;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Usuario;

public class TestePessoaDependentes02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar dtNascimento = Calendar.getInstance();
		dtNascimento.set(87, 3, 26);
		Calendar dtNascimentoA = Calendar.getInstance();
		dtNascimentoA.set(75, 10, 07);
		
		Calendar dtNascimentoB = Calendar.getInstance();
		dtNascimentoB.set(92, 9, 22);
		Calendar dtNascimentoC = Calendar.getInstance();
		dtNascimentoC.set(91, 6, 12);
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Grupo> grupos = new ArrayList<Grupo>();
		List<Esporte> esportes = new ArrayList<Esporte>();
		List<Evento> eventos = new ArrayList<Evento>();
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		
		Esporte esporteA = new Esporte();
		esporteA = espDAO.searchByID(4);
		esportes.add(esporteA);
		
		Esporte esporteB = new Esporte();
		esporteB = espDAO.searchByID(2);
		esportes.add(esporteB);
		
		Esporte esporteC = new Esporte();
		esporteC = espDAO.searchByID(3);
		esportes.add(esporteC);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Guilherme");
		pessoa.setSobrenome("Pereira Medina");
		pessoa.setDtNasc(dtNascimento);
		pessoa.setApelido("Gui");
		pessoa.setTelRes("01154900908");
		pessoa.setCel("011989786554");
		pessoa.setImgPerfil(new byte[3]);
		pessoa.setImgBackGround(new byte[5]);
		pessoa.setEsportes(esportes);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("gm@teste.com");
		usuario.setSenha("123");
		pessoa.setUsuario(usuario);
		
		Endereco endereco = new Endereco();
		endereco.setPais("Brasil");
		endereco.setEstado("SP");
		endereco.setCidade("São Paulo");
		endereco.setBairro("Vila Esperança");
		endereco.setCep("03642000");
		endereco.setRua("Rua José Flávio");
		endereco.setNumero(174);
		endereco.setComplemento("AP. 15"); 
		endereco.setLatitude((float) -23.52459);
		endereco.setLongitude((float) -46.535283);
		pessoa.setCodEndereco(endereco);

		//pessoaDAO.insert(pessoa);

		/*
		//Buscando por ID - PESSOA  ATUALIZANDO
		Pessoa pes = new Pessoa();
		Pessoa pesB = new Pessoa();
		
		//Buscando por ID - GRUPO
		Grupo grupo = new Grupo();
		grupo = grupoDAO.searchByID(4);
		grupos.add(grupo);

		//Buscando por ID - PESSOA
		Pessoa pesC = new Pessoa();
		pesC = pessoaDAO.searchByID(4);
		pesC.setGrupos(grupos);
		pessoaDAO.update(pesC);
		
		//Buscando por ID - EVENTO
		Evento evento = new Evento();
		evento = eventoDAO.searchByID(2);
		eventos.add(evento);
		
		//Buscando por ID - PESSOA
		Pessoa pessoaE = new Pessoa();
		pessoaE = pessoaDAO.searchByID(1);
		pessoaE.setEventos(eventos);
		pessoaDAO.update(pessoaE);
		 */
		
	}

}
