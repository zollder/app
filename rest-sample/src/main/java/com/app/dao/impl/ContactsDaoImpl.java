package com.app.dao.impl;
 
import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ContactsDao;
import com.app.domain.Contact;
 
@Repository
@Transactional
public class ContactsDaoImpl implements ContactsDao
{
	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sFactory)
	{
		this.sessionFactory = sFactory;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Contact loadWithPrimaryKey(Long key)
	{
		return (Contact) sessionFactory.getCurrentSession().get(Contact.class, key);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<Contact> searchContacts(String name)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
		criteria.add(Restrictions.ilike("name", name+"%"));
		return criteria.list();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("unchecked")
	public List<Contact> loadAll()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
		return criteria.list();
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Contact save(Contact entity)
	{
		sessionFactory.getCurrentSession().save(entity);
		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public Contact update(Contact entity)
	{
		try
		{
			sessionFactory.getCurrentSession().saveOrUpdate(entity);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return entity;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void delete(Long key)
	{
		Contact entity = this.loadWithPrimaryKey(key);
		sessionFactory.getCurrentSession().delete(entity);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void refresh(Contact savedEntity)
	{
		sessionFactory.getCurrentSession().refresh(savedEntity);
	}
}