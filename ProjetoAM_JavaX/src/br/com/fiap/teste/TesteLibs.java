package br.com.fiap.teste;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.dao.MensagemGrupoDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.daoimpl.MensagemGrupoDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Confirmacao;
import br.com.fiap.entity.Grupo;
import br.com.fiap.entity.MensagemGrupo;
import br.com.fiap.entity.Pessoa;

public class TesteLibs {
	
	public static void main(String[] args) {
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		GrupoDAO grupoDAO = new GrupoDAOImpl(em);
		MensagemGrupoDAO msgDAO = new MensagemGrupoDAOImpl(em);
		
		Pessoa pessoa = pessoaDAO.buscarInformacoes(3);
		Grupo grupo = grupoDAO.searchByID(1);
		MensagemGrupo msg = new MensagemGrupo();
		msg.setConfirmacao(Confirmacao.SIM);
		msg.setDescricao("Mensagem de Teste.");
		msg.setTitulo("Teste");
		msg.setGrupo(grupo);
		msg.setPessoa(pessoa);
		
		msgDAO.insert(msg);
		
		
	}

}
