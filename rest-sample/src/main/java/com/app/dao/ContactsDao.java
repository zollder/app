package com.app.dao;

import java.util.List;

import com.app.domain.Contact;

public interface ContactsDao
{
	public Contact getById(String id);
	public List<Contact> searchContacts(String name);
	public List<Contact> getAllContacts();
	public int save(Contact contact);
	public void update(Contact contact);
	public void delete(String id);
}