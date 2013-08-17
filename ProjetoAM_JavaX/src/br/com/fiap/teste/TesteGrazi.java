package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.banco.EntityManagerFactorySingleton;
import br.com.fiap.dao.GrupoDAO;
import br.com.fiap.daoimpl.GrupoDAOImpl;
import br.com.fiap.entity.Esporte;
import br.com.fiap.entity.Grupo;

public class TesteGrazi {

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager em = EntityManagerFactorySingleton.getInstance().createEntityManager();
		/*
		Pessoa pes = new Pessoa();
		PessoaDAO pesDAO = new PessoaDAOImpl(em);
		
		pes = pesDAO.buscarInformacoes(2);
		
		System.out.println("Nome: " + pes.getNome());
		System.out.println("Cidade: " + pes.getCodEndereco().getCidade());
		System.out.println("Tel: " + pes.getTelRes());
		*/
		List<Grupo> grupos = new ArrayList<Grupo>();
		Grupo g = new Grupo();
		GrupoDAO gDAO = new GrupoDAOImpl(em); 
		Esporte e = new Esporte();
		
		/*
		g = gDAO.buscaInfoBasicas(2);
		System.out.println("Nome: " + g.getNomeGrupo() );
		System.out.println("Esporte: " + g.getEsportes().set(1, e).getNome());
		System.out.println("Privacidade: " + g.getPrivacidade());
		System.out.println("Quantidade: " + g.getQuantidade());
		Collection<Object[]> resultado = q.getResultList();
		for (Object[] o:resultado)
			System.out.println(o[0] + ", " + o[1]);
		*/
		
		//grupos = gDAO.buscaGruposPorUsuario(1);
		
		//estados.add("PR");
		
		grupos = gDAO.buscaGruposPorUsuario(2);
		System.out.println("COD GRUPO 00 " + grupos.set(0, g).getCodGrupo());
		//System.out.println("NOME GRUPO 00 " + grupos.set(0, g).getNomeGrupo() ) ;
		System.out.println("COD GRUPO 01 " + grupos.set(1, g).getCodGrupo());
		//System.out.println("NOME GRUPO 01 " + grupos.set(1, g).getNomeGrupo());
		/*
		List<Grupo> grups = new ArrayList<Grupo>();
		
		for(Object obj : grups) {  
			  Grupo gru = new Grupo();  
			  gru.setCodGrupo((Integer)obj);
			  gru.setNomeGrupo((String) obj);
			  grups.add(gru);  
			} 
		 */
	}

}
