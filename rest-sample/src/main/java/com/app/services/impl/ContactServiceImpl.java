package com.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ContactsDao;
import com.app.domain.Contact;
import com.app.services.ContactService;

@Service("contactService")
public class ContactServiceImpl implements ContactService
{
    @Autowired
    private ContactsDao contactsDao;

	public Contact getContactById(String id)
	{
		Contact contact = null;
		try
		{
			contact = contactsDao.getById(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return contact;
	}

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

	public List<Contact> getAllContacts()
	{
		List<Contact> contacts = new ArrayList<Contact>();
		try
		{
			contactsDao.getAllContacts();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return contacts;
	}

	public int saveContact(Contact contact)
	{
		int response = 0;
		try
		{
			response = contactsDao.save(contact);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}	

		return response;
	}

	public void updateContact(Contact contact)
	{
		try
		{
			contactsDao.update(contact);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void deleteContact(String id)
	{
		try
		{
			contactsDao.delete(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
