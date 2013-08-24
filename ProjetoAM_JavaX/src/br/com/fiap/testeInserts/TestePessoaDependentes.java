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

public class TestePessoaDependentes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar dtNascimento = Calendar.getInstance();
		dtNascimento.set(85, 1, 22);
		Calendar dtNascimentoA = Calendar.getInstance();
		dtNascimentoA.set(89, 6, 12);
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		EventoDAO eventoDAO = new EventoDAOImpl(em);
		List<Grupo> grupos = new ArrayList<Grupo>();
		List<Esporte> esportes = new ArrayList<Esporte>();
		List<Evento> eventos = new ArrayList<Evento>();

		Esporte esporteA = new Esporte();
		esporteA.setNome("Basquete");
		esportes.add(esporteA);
		
		Esporte esporteB = new Esporte();
		esporteB.setNome("Corrida");
		esportes.add(esporteB);
		
		Esporte esporteC = new Esporte();
		esporteC.setNome("Casual");
		esportes.add(esporteC);
		
		Esporte esporteD = new Esporte();
		esporteD.setNome("Esportes Radicais");
		esportes.add(esporteD);
		
		Esporte esporteE = new Esporte();
		esporteE.setNome("Futebol");
		esportes.add(esporteE);
		
		Esporte esporteF = new Esporte();
		esporteF.setNome("Futebol Americano");
		esportes.add(esporteF);

		Esporte esporteG = new Esporte();
		esporteG.setNome("Tênis");
		esportes.add(esporteG);
		
		Esporte esporteH = new Esporte();
		esporteH.setNome("Vôlei");
		esportes.add(esporteH);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Barbara");
		pessoa.setSobrenome("Alves");
		pessoa.setDtNasc(dtNascimento);
		pessoa.setApelido("BaAlves");
		pessoa.setTelRes("01150789001");
		pessoa.setCel("011990822241");
		pessoa.setImgPerfil(new byte[3]);
		pessoa.setImgBackGround(new byte[5]);
		pessoa.setEsportes(esportes);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("barbara.alves@hotmail.com");
		usuario.setSenha("barbaraII");
		pessoa.setCodUsuario(usuario);
		
		Endereco endereco = new Endereco();
		endereco.setPais("Brasil");
		endereco.setEstado("SP");
		endereco.setCidade("São Paulo");
		endereco.setBairro("Vila Monte Alegre");
		endereco.setCep("04304000");
		endereco.setRua("Avenida Fagundes Filho");
		endereco.setNumero(623);
		endereco.setComplemento(null);
		endereco.setLatitude((float) -23.626926);
		endereco.setLongitude((float) -46.635555);
		pessoa.setCodEndereco(endereco);
		
		//pessoaDAO.insert(pessoa);
		
		/*
		//Buscando por ID - GRUPO
		Grupo grupo = new Grupo();
		grupo = grupoDAO.searchByID(1);
		grupos.add(grupo);
		
		//Buscando por ID - PESSOA
		Pessoa pes = new Pessoa();
		pes = pessoaDAO.searchByID(1);
		
		pes.setGrupos(grupos);
		pessoaDAO.update(pes);
		
		//Buscando por ID - EVENTO
		Evento evento = new Evento();
		evento = eventoDAO.searchByID(1);
		eventos.add(evento);
		
		//Buscando por ID - PESSOA
		Pessoa pessoaE = new Pessoa();
		pessoaE = pessoaDAO.searchByID(2);
		pessoaE.setEventos(eventos);
		pessoaDAO.update(pessoaE);
		 */
		
	}

}
