package com.app.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.app.domain.dto.Person;
import com.app.domain.model.Contact;
import com.app.domain.services.ContactService;

//-------------------------------------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping(value = "/contacts")
public class ContactController
{
	// --------------------------------------------------------------------------------------------------------------------------------
	@Autowired
	private ContactService contactService;

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Retrieves {@link Contact} resource associated to the given key (JSON). */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.GET })
	@ResponseBody
	public Contact loadWithPrimaryKey(@PathVariable Long key)
	{
		Contact contact = contactService.loadWithPrimaryKey(key);

		return contact;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Test */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/person/{key}", method = { RequestMethod.GET }, produces="application/xml")
	@ResponseBody
	public Person loadWithKey(@PathVariable Long key)
	{
		Contact contact = contactService.loadWithPrimaryKey(key);

		Person person = new Person();
		person.setName(contact.getName());
		person.setAddress(contact.getAddress());

		return person;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Inserts the {@link Contact} resource received in the payload. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(method = { RequestMethod.POST })
	@ResponseBody
	public Contact save(@RequestBody Contact contact)
	{
		Contact savedContact = contactService.save(contact);
		contactService.refresh(savedContact);

		return savedContact;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	/** Updates the {@link Contact} resource. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.PUT })
	@ResponseBody
	public Contact update(@PathVariable Long key, @RequestBody Contact contact)
	{
		contact.setId(key);
		Contact updatedContact = contactService.update(contact);
		contactService.refresh(updatedContact);

		return updatedContact;
	}


	// --------------------------------------------------------------------------------------------------------------------------------
	/** Deletes the {@link Contact} resource associated to a given key. */
	// --------------------------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/{key}", method = { RequestMethod.DELETE })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long key)
	{
		contactService.delete(key);
	}
}