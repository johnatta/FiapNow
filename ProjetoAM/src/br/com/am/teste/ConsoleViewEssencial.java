package br.com.am.teste;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.am.dao.EnderecoDAO;
import br.com.am.dao.EntityManagerFactorySingleton;
import br.com.am.dao.EsporteDAO;
import br.com.am.dao.PessoaDAO;
import br.com.am.dao.UsuarioDAO;
import br.com.am.daoimpl.EnderecoDAOImpl;
import br.com.am.daoimpl.EsporteDAOImpl;
import br.com.am.daoimpl.PessoaDAOImpl;
import br.com.am.daoimpl.UsuarioDAOImpl;
import br.com.am.entity.Endereco;
import br.com.am.entity.Esporte;
import br.com.am.entity.Pessoa;
import br.com.am.entity.Usuario;

public class ConsoleViewEssencial {

	public static void main(String[] args) {
		Calendar dtNascimento = Calendar.getInstance();
		dtNascimento.set(30, 1, 22);
		Calendar dtNascimentoA = Calendar.getInstance();
		dtNascimentoA.set(30, 6, 12);
		
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		
		EsporteDAO esporteDAO = new EsporteDAOImpl(em);
		PessoaDAO pessoaDAO = new PessoaDAOImpl(em);
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		EnderecoDAO enderecoDAO = new EnderecoDAOImpl(em);
		
		List<Esporte> esportes = new ArrayList<Esporte>();
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		List<Endereco> enderecos = new ArrayList<Endereco>();

		Esporte esporteA = new Esporte();
		esporteA.setNome("Futebol Americano");
		esportes.add(esporteA);
		
		Esporte esporteB = new Esporte();
		esporteB.setNome("Futebol Society");
		esportes.add(esporteB);
		
		Esporte esporteC = new Esporte();
		esporteC.setNome("Rugby");
		esportes.add(esporteC);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Álvaro Michel");
		pessoa.setSobrenome("Schmidt");
		pessoa.setDtNasc(dtNascimento);
		pessoa.setApelido("AMSheep");
		pessoa.setTelRes("01150099041");
		pessoa.setCel("011967893241");
		pessoa.setImgPerfil(new byte[3]);
		pessoa.setImgBackGround(new byte[5]);
		pessoa.setEsportes(esportes);
		
		Usuario usuario = new Usuario();
		usuario.setEmail("alvaro.schmidt@gmail.com");
		usuario.setSenha("alvaro8790");
		pessoa.setCodUsuario(usuario);
		
		Endereco endereco = new Endereco();
		endereco.setPais("Brasil");
		endereco.setEstado("São Paulo");
		endereco.setCidade("São Paulo");
		endereco.setBairro("São Judas");
		endereco.setCep("04303-191");
		endereco.setRua("Rua Maurício de Lacerda");
		endereco.setNumero(396);
		endereco.setComplemento(null);
		endereco.setLatitude((float) -23.625601);
		endereco.setLongitude((float) -46.635383);
		pessoa.setCodEndereco(endereco);
		
		pessoas.add(pessoa);
		usuarios.add(usuario);
		enderecos.add(endereco);
		
		esporteDAO.insert(esporteA);
		esporteDAO.insert(esporteB); 
		esporteDAO.insert(esporteC); 
		pessoaDAO.insert(pessoa); 
		usuarioDAO.insert(usuario);
		enderecoDAO.insert(endereco); 
		
	}

}
