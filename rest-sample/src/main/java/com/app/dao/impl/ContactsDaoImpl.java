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

	public Contact getById(String id)
	{
		return (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
	}
  
	@SuppressWarnings("unchecked")
	public List<Contact> searchContacts(String name)
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
		criteria.add(Restrictions.ilike("name", name+"%"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getAllContacts()
	{
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class);
		return criteria.list();
	}
	
	public int save(Contact contact)
	{
		return (Integer) sessionFactory.getCurrentSession().save(contact);
	}
	
	public void update(Contact contact)
	{
		sessionFactory.getCurrentSession().merge(contact);
	}
	
	public void delete(String id)
	{
		Contact contact = getById(id);
		sessionFactory.getCurrentSession().delete(contact);
	}
}