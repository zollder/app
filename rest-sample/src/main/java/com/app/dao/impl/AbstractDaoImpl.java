package com.app.dao.impl;
 
import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.app.dao.AbstractDao;

//--------------------------------------------------------------------------------------------------------------------------------
/**
* Base abstract entity DAO. All DAO objects should inherit from this abstract class in order to offer a common basic implementation.
* Note: since Hibernate 3.0.1 the use of HibernateDaoSupport is not required due to availability of Spring transaction support.
*/
//--------------------------------------------------------------------------------------------------------------------------------

public abstract class AbstractDaoImpl<T extends Serializable>  implements AbstractDao<T>
{
	private Class<T> modelClass;
	private SessionFactory sessionFactory;

	public AbstractDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void setModelClass(Class<T> entityClass)
	{
		this.modelClass = entityClass;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Class<T> getModelClass()
	{
		return modelClass;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public T loadWithPrimaryKey(Long key)
	{
		// "get" hits the DB immediately and returns null if there is no matching row.
		// Alternatively, "load" can be used. It creates an association with the object without loading it from the db.
		// It also allows multiple instances to be loaded as a batch (Hibernate 4 reference: Loading an object)
		@SuppressWarnings("unchecked")
		T entity = (T) getCurrentSession().get(getModelClass(), key);

		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public List<T> findAll()
	{
		@SuppressWarnings("unchecked")
		List<T> entities = getCurrentSession().createQuery("from " + getModelClass().getName()).list();

		return entities;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public T save(T entity)
	{
		getCurrentSession().save(entity);
		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Object save(Object object)
	{
		getCurrentSession().save(object);
		return object;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public T update(T entity)
	{
		getCurrentSession().merge(entity);		
		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Object update(Object object)
	{
		getCurrentSession().merge(object);		
		return object;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public boolean delete(Long key)
	{
		T entity = loadWithPrimaryKey(key);
		if (entity == null)
			return false;

		getCurrentSession().delete(entity);
		return true;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void refresh(T savedEntity)
	{
		getCurrentSession().refresh(savedEntity);
	}
}