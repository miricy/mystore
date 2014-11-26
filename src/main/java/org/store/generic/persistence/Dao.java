package org.store.generic.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class Dao<E> {

	@Autowired
	private EntityManagerFactory factory;

	protected Class<? extends Model> clazz;

	public Dao(Class<? extends Model> clazz) {
		this.clazz = clazz;
	}

	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	@Transactional
	public void insert(E object) {
		getEntityManager().persist(object);
	}

	@Transactional
	public void update(E object) {
		getEntityManager().merge(object);
	}

	@Transactional
	public void delete(E object) {
		getEntityManager().remove(object);
	}

	@Transactional
	public List<?> findAll() {
		String queryString = "SELECT a FROM "+clazz.getSimpleName()+" a";
		return getEntityManager().createQuery(queryString).getResultList();
	}

	@Transactional
	public E findById(int id) {
		String queryString = "SELECT a FROM "+clazz.getSimpleName()+" a WHERE a.id = :id";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("id", String.valueOf(id));
		return (E) query.getSingleResult();
	}

	@Transactional
	public E findByField(String field, String value) {
		String queryString = "SELECT a FROM "+clazz.getSimpleName()+" a WHERE a."+field+" = :value";
		Query query = getEntityManager().createQuery(queryString);
		query.setParameter("value", value);
		return (E) query.getSingleResult();
	}
}
