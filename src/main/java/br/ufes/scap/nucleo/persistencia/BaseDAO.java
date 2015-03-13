package br.ufes.scap.nucleo.persistencia;

public interface BaseDAO<T> {
	public void salvar (T object);
	
	T merge(T object);
}
