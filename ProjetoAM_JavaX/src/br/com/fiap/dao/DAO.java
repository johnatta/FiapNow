package br.com.fiap.dao;

public interface DAO<T, K> {
	void insert(T entity);
	
	void remove(T entity);
	
	void removeById(K id);
	
	void update(T entity);

	T searchByID(K id);
	
	T insertEntity(T entity);
}
