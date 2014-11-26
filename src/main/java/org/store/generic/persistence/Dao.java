package org.store.generic.persistence;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

public class Dao<E extends Model> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<? extends Model> clazz;

	public Dao(Class<? extends Model> clazz) {
		this.clazz = clazz;
	}

	public Session getSessionFactory() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (Exception e) {
			return sessionFactory.openSession();
		}
	}

	@Transactional
	public void insert(E object) {
		getSessionFactory().persist(object);
	}

	@Transactional
	public void update(E object) {
		getSessionFactory().merge(object);
	}

	@Transactional
	public void delete(E object) {
		getSessionFactory().delete(object);
	}

	@Transactional
	public List<?> findAll() {
		return getSessionFactory().createCriteria(clazz).list();
	}

	@Transactional
	public E findById(int id) {
		return (E) getSessionFactory().get(clazz, id);
	}

	@Transactional
	public E findByField(String field, String value) {
		return (E) getSessionFactory().createQuery("from "+clazz.getSimpleName()+" where "+field+" = :data").setParameter("data", value).uniqueResult();
	}
}
