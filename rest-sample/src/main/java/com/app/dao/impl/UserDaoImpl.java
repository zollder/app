package com.app.dao.impl;
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserDao;
import com.app.domain.User;
 
@Repository
@Transactional
public class UserDaoImpl implements UserDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sFactory)
	{
		this.sessionFactory = sFactory;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public User loadWithPrimaryKey(Long key)
	{
		// "get" hits the DB immediately and returns null if there is no matching row.
		// Alternatively, "load" can be used. It creates an association with the object without loading it from the db.
		// It also allows multiple instances to be loaded as a batch (Hibernate 4 reference: Loading an object)
		User loadedUser = (User) sessionFactory.getCurrentSession().get(User.class, key);
		return loadedUser;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public User findByUserName(String username)
	{
		// TODO: revise the code: use criteria approach
		Session session = sessionFactory.getCurrentSession();
		String getUserNameQuery = "from User as usr where usr.userName = " + username;
		User user = (User) session.createQuery(getUserNameQuery).uniqueResult();
		return user;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public User save(User entity)
	{
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public User update(User entity)
	{
		sessionFactory.getCurrentSession().merge(entity);		
		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void delete(Long key)
	{
		User entity = this.loadWithPrimaryKey(key);
		sessionFactory.getCurrentSession().delete(entity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void refresh(User savedEntity)
	{
		sessionFactory.getCurrentSession().refresh(savedEntity);
	}
}