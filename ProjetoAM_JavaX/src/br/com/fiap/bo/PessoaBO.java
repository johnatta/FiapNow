package br.com.fiap.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.EsporteDAO;
import br.com.fiap.dao.PessoaDAO;
import br.com.fiap.daoimpl.EsporteDAOImpl;
import br.com.fiap.daoimpl.PessoaDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Pessoa;

public class PessoaBO {
	
	private Pessoa pessoa;
	private PessoaDAO pessoaDAO;
	private EntityManager em;

	public String cadastrar(Pessoa pessoa) {
		
		em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		pessoaDAO = new PessoaDAOImpl(em);
		
		String retorno = "";
		
		//Valores Obrigatórios
		if(pessoa.getNome().equals("") || pessoa.getSobrenome().equals("") || pessoa.getDtNasc().toString().equals("")
				|| pessoa.getApelido().equals("") || pessoa.getImgPerfil().toString().equals("") 
				|| pessoa.getImgBackGround().toString().equals("")) {
			
			retorno = "obrigatority";
			
		} else if (pessoaDAO.buscarPorApelido(pessoa.getApelido()).size() != 0) {
			
			retorno = "invalidNickname";
			
		} else {
			List<Esporte> esportes = pessoa.getEsportes();
			EsporteDAO espDAO = new EsporteDAOImpl(em);
			
			pessoa.setEsportes(new ArrayList<Esporte>());
			
			for(Esporte esp : esportes){
				esp = espDAO.buscarPorNome(esp.getNome());
				pessoa.getEsportes().add(esp);
			}
			
			this.pessoa = pessoaDAO.insertEntity(pessoa);
		}
		
		return retorno;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}
	
	

}
