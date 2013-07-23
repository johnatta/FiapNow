package br.com.am.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
	
	private static EntityManagerFactory factory;
	
	private EntityManagerFactorySingleton() {
		super();
	}
	
	private void sysout() {
		System.out.println(" Fora de  public static EntityManagerFactory getInstance() {");

	}
	
	public static EntityManagerFactory getInstance() {
		System.out.println(" entrou no EntityManagerFactory getInstance");
		if(factory == null){
			System.out.println("antes de factory = Persistence.createEntityManagerF");
			factory = Persistence.createEntityManagerFactory("PROJETO_AM");
		}
		System.out.println("depois do  if factory");
		return factory;
	}
}
