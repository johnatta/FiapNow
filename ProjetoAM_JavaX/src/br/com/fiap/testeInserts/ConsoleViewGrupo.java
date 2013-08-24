package br.com.fiap.testeInserts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.ComentarioGrupoDAO;
import br.com.fiap.dao.ConviteGrupoDAO;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.dao.ModeradorGrupoDAO;
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.ConviteGrupoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.daoimpl.ModeradorGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.ModeradorGrupo;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.entity.Usuario;

public class ConsoleViewGrupo {
	public static void main(String[] args) {
		Calendar dtNascimento = Calendar.getInstance();
		dtNascimento.set(30, 8, 22);
		Calendar dtNascimentoA = Calendar.getInstance();
		dtNascimentoA.set(30, 6, 12);
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		PedidoGrupoDAO pedGrupoDAO = new PedidoGrupoDAOImpl(em);
		ModeradorGrupoDAO modGrupoDAO = new ModeradorGrupoDAOImpl(em);
		ComentarioGrupoDAO comentGrupoDAO = new ComentarioGrupoDAOImpl(em);
		MensagemGrupoDAO msgGrupoDAO = new MensagemGrupoDAOImpl(em);
		ConviteGrupoDAO convtGrupoDAO = new ConviteGrupoDAOImpl(em);
		List<Esporte> esportes = new ArrayList<Esporte>();
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		List<Esporte> esportesP = new ArrayList<Esporte>();
		EsporteDAO espDAO = new EsporteDAOImpl(em);
		

		// ESPORTE - ADICIONANDO A LISTA
		Esporte esporteD = new Esporte();
		esporteD = espDAO.searchByID(6);
		esportesP.add(esporteD);
		
		Esporte esporteC = new Esporte();
		esporteC = espDAO.searchByID(4);
		esportesP.add(esporteC);
		
		// PESSOA
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Álvaro Michel");
		pessoa.setSobrenome("Schmidt");
		pessoa.setDtNasc(dtNascimento);
		pessoa.setApelido("AMSheep");
		pessoa.setTelRes("01150099041");
		pessoa.setCel("011967893241");
		pessoa.setImgPerfil(new byte[3]);
		pessoa.setImgBackGround(new byte[5]);
		pessoa.setEsportes(esportesP);
		
		// USUARIO PARA PESSOA
		Usuario usuario = new Usuario();
		usuario.setEmail("alvaro.schmidt@gmail.com");
		usuario.setSenha("alvaro8790");
		pessoa.setCodUsuario(usuario);
		
		// ENDERECO PARA PESSOA
		Endereco enderecoP = new Endereco();
		enderecoP.setPais("Brasil");
		enderecoP.setEstado("SP");
		enderecoP.setCidade("São Paulo");
		enderecoP.setBairro("São Judas");
		enderecoP.setCep("04303191");
		enderecoP.setRua("Rua Maurício de Lacerda");
		enderecoP.setNumero(396);
		enderecoP.setComplemento(null);
		enderecoP.setLatitude((float) -23.625601);
		enderecoP.setLongitude((float) -46.635383);
		pessoa.setCodEndereco(enderecoP);

		// INSERINDO PESSOA
		pessoaDAO.insert(pessoa); 
		
		// CRIANDO GRUPO
		Grupo grupo = new Grupo();
		grupo.setNomeGrupo("Bandeirantes Raptors");
		grupo.setDescricao("Somos os Bandeirantes Raptors queremos construir um grupo que goste e pratique Futebol Americano. " +
				"Todo o membro será bem-vindo para compartilhar das mesmas paixões. " +
				"GO B.Raptors!");
		grupo.setPrivacidade(Privacidade.Aberto);
		Pessoa pesADM = new Pessoa();
		pesADM = pessoaDAO.searchByID(1);
		grupo.setAdm(pesADM);
		grupo.setImgGrupo(new byte [4]);
		
		// ESPORTES PARA GRUPO
		//Esporte esporteA = new Esporte();
		//
		
		//Esporte esporteB = new Esporte();
		//
		
		esportes.add(esporteD);
		//esportes.add(esporteB);
		grupo.setEsportes(esportes);
		
		// INSERINDO GRUPO
		grupoDAO.insert(grupo);
		
		// PEDIDO GRUPO
		PedidoGrupo pedidoGrupo = new PedidoGrupo();
		pedidoGrupo.setDescricao("Eu desejo participar pois sou apaixonado por FA.");
		pedidoGrupo.setCodGrupo(grupo);
		pedidoGrupo.setCodPessoa(pessoa);
		// INSERINDO PEDIDO
		pedGrupoDAO.insert(pedidoGrupo);
		
		//MODERADOR GRUPO
		ModeradorGrupo modGrupo = new ModeradorGrupo();
		modGrupo.setCodGrupo(grupo);
		modGrupo.setCodPessoa(pessoa);
		// INSERINDO MODERADOR
		modGrupoDAO.insert(modGrupo);
		
		//COMENTARIO NO GRUPO
		ComentarioGrupo comentGrupo = new ComentarioGrupo();
		comentGrupo.setComentario("Curti muito a iniciativa de criar um grupo para ambos esporte que pratico. Temos que combinar de jogar ainda neste mês.");
		comentGrupo.setDataHora(Calendar.getInstance());
		comentGrupo.setCodGrupo(grupo);
		comentGrupo.setCodPessoa(pessoa);
		// INSERINDO COMENTARIO
		comentGrupoDAO.insert(comentGrupo);
		
		// CRIANDO MSG GRUPO
		MensagemGrupo msgGrupo = new MensagemGrupo();
		msgGrupo.setDescricao("Seja bem-vindo ao nosso grupo Bandeirantes Raptors!");
		msgGrupo.setConfirmacao(Confirmacao.SIM);
		msgGrupo.setCodGrupo(grupo);
		msgGrupo.setCodPessoa(pessoa);
		// INSERINDO MSG GRUPO
		msgGrupoDAO.insert(msgGrupo);
		
		// CONVITE GRUPO
		ConviteGrupo convtGrupo = new ConviteGrupo();
		convtGrupo.setDescricao("Vi que você gosta de Futebol americano, gostaria de participar do meu grupo ?.");
		convtGrupo.setCodGrupo(grupo);
		convtGrupo.setCodPessoa(pessoa);
		//INSERINDO CONVITE GRUPO
		convtGrupoDAO.insert(convtGrupo);
	}
}
