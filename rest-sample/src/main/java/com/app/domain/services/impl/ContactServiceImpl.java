package com.app.domain.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ContactsDao;
import com.app.domain.model.Contact;
import com.app.domain.services.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService
{
    @Autowired
    private ContactsDao contactsDao;

    // --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
	public Contact loadWithPrimaryKey(Long key)
	{
		// TODO:
//		if (entity == null)
//		throw new DataNotFoundException(String.format("User with username '%s' not found", id));

		Contact contactEntity = contactsDao.loadWithPrimaryKey(key);

		return contactEntity;
	}

    // --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
	public List<Contact> getContactsByName(String name)
	{
		List<Contact> contacts = new ArrayList<Contact>();
		try
		{
			contactsDao.searchContacts(name);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return contacts;
	}

    // --------------------------------------------------------------------------------------------------------------------------------
    @Transactional(readOnly = true)
	public List<Contact> loadAll()
	{
		List<Contact> contacts = contactsDao.loadAll();

		return contacts;
	}

    // --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public Contact save(Contact contact)
	{
		Contact savedContact = contactsDao.save(contact);

		return savedContact;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	@Transactional
	public Contact update(Contact contact)
	{
		Contact updatedContact = contactsDao.update(contact);

		return updatedContact;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	public void delete(Long key)
	{
			contactsDao.delete(key);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Refreshes saved contact so ensure correct serialisation/marshalling (especially for @ManyToOne relations) */
	// --------------------------------------------------------------------------------------------------------------------------------
	public void refresh(Contact savedContact)
	{
		contactsDao.refresh(savedContact);		
	}
}
