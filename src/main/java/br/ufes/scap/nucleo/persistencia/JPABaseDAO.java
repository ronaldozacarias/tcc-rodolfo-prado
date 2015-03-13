package br.ufes.scap.nucleo.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class JPABaseDAO<T> implements BaseDAO<T>{
	
	@PersistenceContext
	private EntityManager manager;
	
	public JPABaseDAO(){
		
	}
	
	protected EntityManager getEntityManager() {
		return manager;
	}

	@Override
	public void salvar(T object) {
		manager.persist(object);
		
	}
	
	@Override
	public T merge(T object) {			
		manager.merge(object);
		return manager.merge(object);
		
	}
	
	public void delete(T object) {		
		manager.remove(manager.merge(object));
	}

}
