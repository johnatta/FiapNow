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
import br.com.fiap.dao.PedidoGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.ComentarioGrupoDAOImpl;
import br.com.fiap.daoimpl.ConviteGrupoDAOImpl;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.daoimpl.PedidoGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.ComentarioGrupo;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.ConviteGrupo;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.PedidoGrupo;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.entity.Privacidade;
import br.com.fiap.entity.Usuario;

public class ConsoleViewGrupo02 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Calendar dtNascimento = Calendar.getInstance();
		dtNascimento.set(2012, 10, 22);
		Calendar dtNascimentoA = Calendar.getInstance();
		dtNascimentoA.set(1991, 8, 20);
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		PedidoGrupoDAO pedGrupoDAO = new PedidoGrupoDAOImpl(em);
		ComentarioGrupoDAO comentGrupoDAO = new ComentarioGrupoDAOImpl(em);
		MensagemGrupoDAO msgGrupoDAO = new MensagemGrupoDAOImpl(em);
		ConviteGrupoDAO convtGrupoDAO = new ConviteGrupoDAOImpl(em);
		List<Esporte> esportes = new ArrayList<Esporte>();
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		EsporteDAO espDAO = new EsporteDAOImpl(em);

		List<Esporte> esportesP = new ArrayList<Esporte>();

		Esporte esporteD = new Esporte();
		esporteD = espDAO.searchByID(2);
		esportesP.add(esporteD);
		
		Esporte esporteC = new Esporte();
		esporteC = espDAO.searchByID(7);
		esportesP.add(esporteC);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Camila");
		pessoa.setSobrenome("Andrade");
		pessoa.setDtNasc(dtNascimentoA);
		pessoa.setApelido("Milla");
		pessoa.setTelRes("01140099041");
		pessoa.setCel("011978003241");
		pessoa.setImgPerfil(new byte[3]);
		pessoa.setImgBackGround(new byte[5]);
		pessoa.setEsportes(esportes);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("ca@gmail.com");
		usuario.setSenha("123");
		pessoa.setUsuario(usuario);
		
		Endereco enderecoP = new Endereco();
		enderecoP.setPais("Brasil");
		enderecoP.setEstado("SP");
		enderecoP.setCidade("São Paulo");
		enderecoP.setBairro("Moema");
		enderecoP.setCep("04522034");
		enderecoP.setRua("Rua Gaivota");
		enderecoP.setNumero(1027);
		enderecoP.setComplemento(null);
		enderecoP.setLatitude((float) -23.604525);
		enderecoP.setLongitude((float) -46.668428);
		pessoa.setEndereco(enderecoP);
		
		pessoaDAO.insert(pessoa); 
		
		Grupo grupo = new Grupo();
		grupo.setNomeGrupo("Corredores Incasáveis");
		grupo.setDescricao("Somos um grupo formando por pessoas que gostam de se exercitar, tendo as praticas de corrida como " +
				"principal paixão. Aqui nos temos interesse de unir pessoas para corrida em dupla ou grupo.");
		grupo.setPrivacidade(Privacidade.Aberto);
		grupo.setImgGrupo(new byte [4]);
		Pessoa pesADM = new Pessoa();
		pesADM = pessoaDAO.searchByID(3);
		grupo.setAdm(pesADM);
		//Esporte esporteA = new Esporte();
		//esporteA.setNome("Corrida");
		//Esporte esporteB = new Esporte();
		//esporteB.setNome("Rugby");
		//esportes.add(esporteB);
		esportes.add(esporteD);
		grupo.setEsportes(esportes);
		grupoDAO.insert(grupo);
		
		PedidoGrupo pedidoGrupo = new PedidoGrupo();
		pedidoGrupo.setDescricao("Eu desejo participar pois amo correr.");
		pedidoGrupo.setGrupo(grupo);
		pedidoGrupo.setPessoa(pessoa);
		pedGrupoDAO.insert(pedidoGrupo);

		/*ModeradorGrupo modGrupo = new ModeradorGrupo();
		modGrupo.setGrupo(grupo);
		modGrupo.setPessoa(pessoa);
		modGrupoDAO.insert(modGrupo);*/
		
		//Ariel Molina - Nova forma de se inserir moderador
		Pessoa moderador = new Pessoa();
		moderador = pessoaDAO.searchByID(1);
		if(grupo.getModeradores() == null){
			List<Pessoa> moderadores = new ArrayList<Pessoa>();
			moderadores.add(moderador);
			grupo.setModeradores(moderadores);
			grupo.setMembros(moderadores);
		}else {
			grupo.getModeradores().add(moderador);
		}
		
		ComentarioGrupo comentGrupo = new ComentarioGrupo();
		comentGrupo.setComentario("Poderíamos fazer um evento para corrida no Parque do Ibirapuera.");
		comentGrupo.setDataHora(Calendar.getInstance());
		comentGrupo.setGrupo(grupo);
		comentGrupo.setPessoa(pessoa);
		comentGrupoDAO.insert(comentGrupo);
		
		MensagemGrupo msgGrupo = new MensagemGrupo();
		msgGrupo.setTitulo("Bem-vindo!");
		msgGrupo.setDescricao("Seja bem-vindo ao nosso grupo!");
		msgGrupo.setConfirmacao(Confirmacao.SIM);
		msgGrupo.setGrupo(grupo);
		msgGrupo.setPessoa(pessoa);
		
		msgGrupoDAO.insert(msgGrupo);
		
		ConviteGrupo convtGrupo = new ConviteGrupo();
		convtGrupo.setDescricao("Gostaria de participar do meu grupo: Corredores Incasáveis.");
		convtGrupo.setGrupo(grupo);
		convtGrupo.setPessoa(pessoa);
		convtGrupoDAO.insert(convtGrupo);
	}

}