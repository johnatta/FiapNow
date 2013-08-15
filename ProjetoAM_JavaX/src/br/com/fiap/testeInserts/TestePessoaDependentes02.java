package br.com.fiap.testeInserts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EventoDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.PessoaDAO;
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

		Esporte esporteA = new Esporte();
		esporteA.setNome("Skate");
		esportes.add(esporteA);
		
		Esporte esporteB = new Esporte();
		esporteB.setNome("Ciclismo");
		esportes.add(esporteB);
		
		Esporte esporteC = new Esporte();
		esporteC.setNome("Video Game");
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
		usuario.setEmail("gui.medina@yahoo.com.br");
		usuario.setSenha("gui0201");
		pessoa.setCodUsuario(usuario);
		
		Endereco endereco = new Endereco();
		endereco.setPais("Brasil");
		endereco.setEstado("SP");
		endereco.setCidade("S�o Paulo");
		endereco.setBairro("Vila Esperan�a");
		endereco.setCep("03642000");
		endereco.setRua("Rua Jos� Fl�vio");
		endereco.setNumero(174);
		endereco.setComplemento("AP. 15"); 
		endereco.setLatitude((float) -23.52459);
		endereco.setLongitude((float) -46.535283);
		pessoa.setCodEndereco(endereco);

		//pessoaDAO.insert(pessoa);

		//Buscando por ID - PESSOA  ATUALIZANDO
		Pessoa pes = new Pessoa();
		Pessoa pesB = new Pessoa();
		pes = pessoaDAO.searchByID(2);
		pesB = pessoaDAO.searchByID(3);
		pes.setDtNasc(dtNascimentoB);
		pesB.setDtNasc(dtNascimentoC);
		//pessoaDAO.update(pes);
		//pessoaDAO.update(pesB);
		/*
		
		//Buscando por ID - GRUPO
		Grupo grupo = new Grupo();
		grupo = grupoDAO.searchByID(4);
		grupos.add(grupo);
		
		//Buscando por ID - PESSOA
		Pessoa pesC = new Pessoa();
		pesC = pessoaDAO.searchByID(5);
		
		pesC.setGrupos(grupos);
		pessoaDAO.update(pesC);

		//Buscando por ID - EVENTO
		Evento evento = new Evento();
		evento = eventoDAO.searchByID(2);
		eventos.add(evento);
		
		//Buscando por ID - PESSOA
		Pessoa pessoaE = new Pessoa();
		pessoaE = pessoaDAO.searchByID(4);
		pessoaE.setEventos(eventos);
		pessoaDAO.update(pessoaE);
		 */
		
	}

}