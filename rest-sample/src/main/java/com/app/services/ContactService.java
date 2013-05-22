package com.app.services;

import java.util.List;

import com.app.domain.Contact;


public interface ContactService
{
	public Contact getContactById(String id);
	public List<Contact> getContactsByName(String name);
	public List<Contact> getAllContacts();
	public int saveContact(Contact contact);
	public void updateContact(Contact contact);
	public void deleteContact(String id);
}