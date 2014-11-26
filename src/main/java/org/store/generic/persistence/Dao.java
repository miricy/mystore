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
		sessionFactory.getCurrentSession().persist(object);
	}

	@Transactional
	public void update(E object) {
		sessionFactory.getCurrentSession().merge(object);
	}

	@Transactional
	public void delete(E object) {
		sessionFactory.getCurrentSession().delete(object);
	}

	@Transactional
	public List<?> findAll() {
		return sessionFactory.getCurrentSession().createCriteria(clazz).list();
	}

	@Transactional
	public E findById(int id) {
		return (E) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@Transactional
	public E findByField(String field, String value) {
		return (E) getSessionFactory().createQuery("from "+clazz.getSimpleName()+" where "+field+" = :data").setParameter("data", value).uniqueResult();
	}
}
